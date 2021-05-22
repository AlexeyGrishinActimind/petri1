package com.actimind.petri.model;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface TokenConsumer {

    //todo: rename
    List<Token> consume(List<Token> input);

    final TokenConsumer DEFAULT = (input) -> input.stream().limit(1).collect(Collectors.toList());
}
