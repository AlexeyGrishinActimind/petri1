package com.actimind.petri.model;
import java.util.Collections;
import java.util.List;

@FunctionalInterface
interface TokenProducer {
    List<Token> produce();

    TokenProducer DEFAULT = () -> Collections.singletonList(new Token());

}