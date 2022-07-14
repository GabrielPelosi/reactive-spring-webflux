package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();


    @Test
    void namesFlux() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("alex","ben","chloe")
                .verifyComplete();
    }

    @Test
    void namesFluxMap() {
        Flux<String> namesFluxMap = fluxAndMonoGeneratorService.namesFluxMap();

        StepVerifier.create(namesFluxMap).expectNext("ALEX","BEN","CHLOE").verifyComplete();
    }

    @Test
    void nameMono() {
        Mono<String> nameMono = fluxAndMonoGeneratorService.nameMono();

        StepVerifier.create(nameMono).expectNext("alex").verifyComplete();

    }

    @Test
    void namesFluxImmutability() {

        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFluxImmutability();

        StepVerifier.create(namesFlux)
                .expectNext("alex","ben","chloe")
                .verifyComplete();


    }

    @Test
    void namesFluxFilter() {

        int stringLength = 4;
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFluxFilter(stringLength);

        StepVerifier.create(namesFlux)
                .expectNext("CHLOE")
                .verifyComplete();



    }

    @Test
    void main() {
    }

    @Test
    void namesFluxFlatMap() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap();

        StepVerifier.create(namesFlux)
                .expectNext("a","l","e","x")
                .verifyComplete();

    }

    @Test
    void namesFluxFlatMap2() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap2();

        StepVerifier.create(namesFlux)
                .expectNext("A","L","E","X")
                .verifyComplete();
    }

    @Test
    void nameMono_flatMap() {

        Mono<List<String>> namesMono = fluxAndMonoGeneratorService.nameMono_flatMap();

        StepVerifier.create(namesMono)
                .expectNext(List.of("a","l","e","x"))
                .verifyComplete();

    }

    @Test
    void nameMono_flatMapMany() {

        Flux<String> namesFlux = fluxAndMonoGeneratorService.nameMono_flatMapMany();

        StepVerifier.create(namesFlux)
                .expectNext("a","l","e","x")
                .verifyComplete();

    }

    @Test
    void fluxTransform() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.fluxTransform();

        StepVerifier.create(namesFlux)
                .expectNext("ALEX", "CHLOE")
                .verifyComplete();

    }

    @Test
    void fluxDefaultEmpty() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.fluxDefaultEmpty();
        StepVerifier.create(namesFlux).expectNext("default").verifyComplete();
    }

    @Test
    void fluxSwitchEmpty() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.fluxSwitchEmpty();
        StepVerifier.create(namesFlux).expectNext("default1", "default2").verifyComplete();
    }

    @Test
    void fluxConcatExample() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.fluxConcatExample();
        StepVerifier.create(namesFlux).expectNext("A", "B", "C", "D", "E" ,"F").verifyComplete();
    }

    @Test
    void fluxConcatExample2() {
        Flux<String> namesFlux = fluxAndMonoGeneratorService.fluxConcatExample2();
        StepVerifier.create(namesFlux).expectNext("A", "B", "C", "D", "E" ,"F").verifyComplete();
    }
}