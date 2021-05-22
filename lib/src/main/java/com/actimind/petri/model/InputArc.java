package com.actimind.petri.model;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class InputArc extends DataHolder<InputArc> {

    @NonNull @Getter private final Place place;
    @NonNull @Getter private final Transition transition;
    private final TokenConsumer tokenConsumer;

    public InputArc(@NonNull Place place, @NonNull Transition transition) {
        this(place, transition, TokenConsumer.DEFAULT);
    }

    public List<Token> tryConsume() {
        return tokenConsumer.consume(place.getTokens());
    }

    public void consume() {
        var consumed = tokenConsumer.consume(place.getTokens());
        place.removeAll(consumed);
    }

    //todo: move out and make helpers "markVisited" and "isVisited"
    static class Visited {
    }

}
