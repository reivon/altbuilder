package fr.reivon.altbuilder.repository;

import fr.reivon.altbuilder.domain.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String roleName);
}
