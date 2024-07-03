package fr.reivon.altbuilder.repository;

import fr.reivon.altbuilder.domain.user.Jwt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JwtRepository extends CrudRepository<Jwt, Long> {
    Optional<Jwt> findByToken(String token);

    @Query("FROM Jwt j where j.expired = :expired AND j.disabled = :disabled AND j.customer.email = :customerEmail")
    Optional<Jwt> findCustomerValidToken(String customerEmail, boolean expired, boolean disabled);

    @Query("FROM Jwt j where j.refreshTokenJwt.token = :token")
    Optional<Jwt> findByRefreshTokenJwt(String token);

    void deleteAllByExpiredAndDisabled(boolean expired, boolean disabled);
}
