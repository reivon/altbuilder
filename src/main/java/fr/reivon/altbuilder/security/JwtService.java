package fr.reivon.altbuilder.security;

import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.service.CustomerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class JwtService {

    public static final String ENCRYPTION_KEY = "d7d76a988b5638cc4e10ba46595e386053c32c57d5cd2502a6e109ba77150582";

    private CustomerService customerService;

    public Map<String, String> generate(String username) {
        Customer customer = customerService.loadUserByUsername(username);
        return generateJwt(customer);
    }

    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
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
        return Map.of("bearer", bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }
}
