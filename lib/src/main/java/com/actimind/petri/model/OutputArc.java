package com.actimind.petri.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class OutputArc extends DataHolder<OutputArc> {

    @NonNull @Getter private final Transition transition;
    @NonNull @Getter private final Place place;
    @NonNull private final TokenProducer tokenProducer;

    public OutputArc(Transition transition, Place place) {
        this(transition, place, TokenProducer.DEFAULT);
    }

    public void produce() {
        place.addAll(tokenProducer.produce());
    }

}
