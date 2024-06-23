package fr.reivon.altbuilder.web.rest.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Jacksonized
public class EditionDto {

    Long id;
    String nameVo;
    String nameVf;

}
