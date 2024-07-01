package fr.reivon.altbuilder.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "project", ignoreUnknownFields = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectConfiguration {

    public String test;
    public Api api;

    public record Api(
            String url
    ) {}

}
