package fr.reivon.altbuilder.repository;

import fr.reivon.altbuilder.domain.user.Privilege;
import fr.reivon.altbuilder.domain.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    Privilege findByName(String name);
}
