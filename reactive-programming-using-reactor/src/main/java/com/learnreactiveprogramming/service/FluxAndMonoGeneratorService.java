package com.learnreactiveprogramming.service;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux () {
        return Flux.fromIterable(List.of("alex", "ben", "chloe")).log();
    }

    public Flux<String> namesFluxMap () {
        return Flux.fromIterable(List.of("alex", "ben", "chloe")).map(String::toUpperCase).log();
    }

    public Flux<String> namesFluxFilter (int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .log();
    }

    public Flux<String> namesFluxImmutability () {
        Flux<String> namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase).log();
        return namesFlux;
    }

    public Flux<String> namesFluxFlatMap () {
        Function<String, Publisher<String>> mapper = s -> Flux.just(s.split(""));

        return Flux.fromIterable(List.of("alex"))
                .flatMap(mapper)
                .log();
    }

    public Flux<String> namesFluxFlatMap2 () {

        return Flux.fromIterable(List.of("alex"))
                .flatMap(s -> splitName(s))
                .log();
    }

    private Flux<String> splitName(String s) {
        return Flux.fromArray(s.toUpperCase().split(""));
    }

    public Mono<String> nameMono () {
        return Mono.just("alex").log();
    }

    public Mono<List<String>> nameMono_flatMap () {
        return Mono.just("alex")
                .flatMap(s -> splitString(s))
                .log();
    }

    private Mono<List<String>> splitString(String s) {
        return Mono.just(List.of(s.split("")));
    }

    public Flux<String> nameMono_flatMapMany () {
        Function<String, Publisher<String>> mapper = s -> Flux.just(s.split(""));
        return Mono.just("alex")
                .flatMapMany(mapper)
                .log();
    }

    public Flux<String> fluxTransform () {
        Function<Flux<String>, Flux<String>> mapper = f -> f.map(String::toUpperCase)
                .filter(s -> s.length() > 3);
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(mapper)
                .log();
    }

    public Flux<String> fluxDefaultEmpty () {
        return Flux.fromIterable(List.of("alex", "ben", "chloe")).filter( s -> !(s instanceof String))
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> fluxSwitchEmpty () {
        return Flux.fromIterable(List.of("alex", "ben", "chloe")).filter( s -> !(s instanceof String))
                .switchIfEmpty(Flux.fromIterable(List.of("default1","default2")))
                .log();
    }

    public Flux<String> fluxConcatExample () {

        Flux<String> flux1 = Flux.fromIterable(List.of("A","B","C"));
        Flux<String> flux2 = Flux.fromIterable(List.of("D","E","F"));

        return Flux.concat(flux1, flux2);
    }

    public Flux<String> fluxConcatExample2 () {

        Flux<String> flux1 = Flux.fromIterable(List.of("A","B","C"));
        Flux<String> flux2 = Flux.fromIterable(List.of("D","E","F"));

        return flux1.concatWith(flux2);
    }


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> {
                    System.out.println("Flux names are " + name);
                });

        fluxAndMonoGeneratorService.nameMono().subscribe(name -> System.out.println("Mono name is: " + name));

    }
}
