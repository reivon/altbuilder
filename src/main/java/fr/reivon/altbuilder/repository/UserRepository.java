package fr.reivon.altbuilder.repository;

import fr.reivon.altbuilder.domain.user.Customer;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Customer, Long> {

}
