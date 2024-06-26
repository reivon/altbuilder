package fr.reivon.altbuilder.web.rest.dto.customer;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Jacksonized
public class CustomerDto {

    Long id;
    String nickname;
    String email;

}
