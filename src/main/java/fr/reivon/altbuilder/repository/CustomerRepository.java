package fr.reivon.altbuilder.repository;

import fr.reivon.altbuilder.domain.user.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(String username);
}
