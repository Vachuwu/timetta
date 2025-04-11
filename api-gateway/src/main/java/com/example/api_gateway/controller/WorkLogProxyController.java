package com.example.api_gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/worklogs")
@RequiredArgsConstructor
public class WorkLogProxyController {

    private final WebClient webClient;

    @Value("${core.service.base-url}")
    private String baseUrl;

    @PostMapping
    public Mono<String> create(@RequestBody String body) {
        return webClient.post()
                .uri(baseUrl + "worklogs")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/{id}")
    public Mono<String> getById(@PathVariable Long id) {
        return webClient.get()
                .uri(baseUrl + "worklogs/" + id)
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping
    public Flux<String> getAll() {
        return webClient.get()
                .uri(baseUrl + "worklogs")
                .retrieve()
                .bodyToFlux(String.class);
    }

    @GetMapping("/user/{userId}")
    public Flux<String> getAllByUserId(@PathVariable Long userId) {
        return webClient.get()
                .uri(baseUrl + "worklogs/user/" + userId)
                .retrieve()
                .bodyToFlux(String.class);
    }

    @PutMapping("/{id}")
    public Mono<String> update(@PathVariable Long id, @RequestBody String body) {
        return webClient.put()
                .uri(baseUrl + "worklogs/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return webClient.delete()
                .uri(baseUrl + "worklogs/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
