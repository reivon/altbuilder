package fr.reivon.altbuilder.web.rest.dto.card;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Jacksonized
public class CardSubTypeDto {

    Long id;
    String nameVo;
    String nameVf;

}
