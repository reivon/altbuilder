package fr.reivon.altbuilder.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Jwt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    private boolean disabled;
    private boolean expired;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Customer customer;
}
