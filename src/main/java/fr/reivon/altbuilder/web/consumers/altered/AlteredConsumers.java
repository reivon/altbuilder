package fr.reivon.altbuilder.web.consumers.altered;


import fr.reivon.altbuilder.config.ProjectConfiguration;
import fr.reivon.altbuilder.service.CardService;
import fr.reivon.altbuilder.web.consumers.altered.dto.ConsumerResponse;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AlteredConsumers {

    ProjectConfiguration projectConfiguration;
    CardService cardService;

    public AlteredConsumers(ProjectConfiguration projectConfiguration, CardService cardService) {
        this.projectConfiguration = projectConfiguration;
        this.cardService = cardService;
    }

    public void getAllCard() throws SSLException {
        String temps = projectConfiguration.api.url();

        String url = "https://api.altered.gg/cards";

        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));


        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        Mono<ConsumerResponse> consumerCardFlux = client.get()
                .uri(url)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "fr-FR")
                .retrieve()
                .bodyToMono(ConsumerResponse.class)
                .onErrorResume(Exception.class, e -> Mono.empty());

        consumerCardFlux.subscribe(cards -> {
            log.info("consumer card: {}", cards);
            cardService.saveOrUpdate(cards.cards());
        });

    }

}
