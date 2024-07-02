package fr.reivon.altbuilder.security;

import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.domain.user.Jwt;
import fr.reivon.altbuilder.repository.JwtRepository;
import fr.reivon.altbuilder.service.CustomerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@AllArgsConstructor
@Service
public class JwtService {

    public static final String ENCRYPTION_KEY = "d7d76a988b5638cc4e10ba46595e386053c32c57d5cd2502a6e109ba77150582";
    public static final String BEARER = "bearer";

    private CustomerService customerService;
    private JwtRepository jwtRepository;

    public Map<String, String> generate(String username) {
        Customer customer = customerService.loadUserByUsername(username);
        Map<String, String> jwtMap = generateJwt(customer);

        final Jwt jwt = Jwt.builder().token(jwtMap.get(BEARER)).disabled(false).expired(false).customer(customer).build();
        jwtRepository.save(jwt);

        return jwtMap;
    }

    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public Jwt tokenByValue(String token) {
        return jwtRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Unknown token"));
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Map<String, String> generateJwt(Customer customer) {
        long currentTimeMillis = System.currentTimeMillis();
        Date expirationTime = new Date(currentTimeMillis + (30 * 60 * 1000));
        Map<String, Object> claims = Map.of(
                "nickname", customer.getNickname(),
                Claims.SUBJECT, customer.getEmail(),
                Claims.EXPIRATION, expirationTime
        );
        final String bearer = Jwts.builder()
                .issuedAt(new Date(currentTimeMillis))
                .expiration(expirationTime)
                .subject(customer.getEmail())
                .claims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        ;
        return Map.of(BEARER, bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public void logout() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Jwt jwt = jwtRepository.findCustomerValidToken(customer.getEmail(), false, false)
                .orElseThrow(() -> new RuntimeException("Unknown token"));
        jwt.setExpired(true);
        jwt.setDisabled(true);
        jwtRepository.save(jwt);
    }

    // each minute
    @Scheduled(cron = "0 */1 * * * *")
    public void removeUselessJwt() {
        log.info("Delete useless jwt launch {}", Instant.now());
        jwtRepository.deleteAllByExpiredAndDisabled(true, true);
    }

}
