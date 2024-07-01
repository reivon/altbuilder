package fr.reivon.altbuilder;

import fr.reivon.altbuilder.config.ProjectConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ProjectConfiguration.class)
@TestPropertySource("classpath:application-test.yml")
public class ConfigLoadedTest {

    @Autowired
    private ProjectConfiguration projectConfiguration;

    //@Test
    void givenUserDefinedPOJO_whenBindingPropertiesFile_thenAllFieldsAreSet() {
        assertEquals("titi", projectConfiguration.getTest());
        assertEquals("https://api.altered.gg/cards", projectConfiguration.getApi().url());
    }
}