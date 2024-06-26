package fr.reivon.altbuilder.service;

import fr.reivon.altbuilder.domain.UserRole;
import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.domain.user.Role;
import fr.reivon.altbuilder.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService implements UserDetailsService {

    private BCryptPasswordEncoder passwordEncoder;
    private CustomerRepository customerRepository;

    public void registration(Customer customer) {
        if (customer.getEmail().indexOf('@') == -1) {
            throw new RuntimeException("Email invalide");
        }

        Optional<Customer> user = customerRepository.findByEmail(customer.getUsername());
        if (user.isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }


        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Role role = Role.builder()
                .name(UserRole.USER)
                .build();

        customer.setRole(role);

        // FIXME
        customer.setEnabled(Boolean.TRUE);

        customerRepository.save(customer);
    }

    @Override
    public Customer loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Pas de compte sur l'email suivant : " + username));
    }

}
