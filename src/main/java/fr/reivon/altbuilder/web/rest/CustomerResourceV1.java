package fr.reivon.altbuilder.web.rest;

import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.security.JwtService;
import fr.reivon.altbuilder.service.CustomerService;
import fr.reivon.altbuilder.web.rest.dto.customer.AuthenticationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/customer")
public class CustomerResourceV1 {

    private AuthenticationManager authenticationManager;
    private CustomerService customerService;
    private JwtService jwtService;

    @PostMapping("/registration")
    public void inscription(@RequestBody Customer customer) {
        customerService.registration(customer);
    }

    @PostMapping("/login")
    public Map<String, String> connection(@RequestBody AuthenticationDto authenticationDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password())
        );
        log.info("Tentative d'authen, resultat : {}", authenticate.isAuthenticated());
        if (authenticate.isAuthenticated()) {
            return jwtService.generate(authenticationDto.username());
        }
        return null;
    }

    @PostMapping("/logout")
    public void logout() {
        jwtService.logout();
    }

    // TODO : manage a refresh token system : https://www.youtube.com/watch?v=KoBGERCIXag
    // TODO : manage an update password : https://www.youtube.com/watch?v=gFu0yoAlJDA
    // TODO : manage role and permission : https://www.youtube.com/watch?v=CHrwipNMiY4

}
