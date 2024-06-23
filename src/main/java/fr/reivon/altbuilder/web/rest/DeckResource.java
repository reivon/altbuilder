package fr.reivon.altbuilder.web.rest;

import fr.reivon.altbuilder.web.rest.dto.DeckDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("deck")
public class DeckResource {

    private static final Logger log = LoggerFactory.getLogger(DeckResource.class);

    @GetMapping("example")
    @ResponseStatus(HttpStatus.OK)
    public DeckDto getDeck() {
        return null;
    }

}
