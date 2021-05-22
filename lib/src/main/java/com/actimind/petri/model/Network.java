package com.actimind.petri.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Network {

    private int seed;
    private Random random = new Random(seed);

    private final Map<String, Place> places = new HashMap<>();
    private final Map<String, Transition> transitions = new HashMap<>();

    private final Map<String, List<InputArc>> inputArcs = new HashMap<>();
    private final Map<String, List<OutputArc>> outputArcs = new HashMap<>();

    private int id = 1;

    private String genId() {
        return "" + id++;
    }

    public void setSeed(int seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    public int getRandomInt() {
        return this.random.nextInt();
    }

    public <T> T getRandomItem(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    public Place place(String title) {
        Place place = new Place(genId(), title);
        places.put(place.getId(), place);
        return place;
    }

    public TransitionBuilder transition(String title) {
        Transition transition = new Transition(genId(), title);
        transitions.put(transition.getId(), transition);
        return new TransitionBuilder(transition);
    }

    public Token token() {
        return new Token();
    }

    public InputArcBuilder connect(Transition transition, Place place) {
        var arc = new InputArc(place, transition);
        inputArcs.computeIfAbsent(place.getId(), s -> new ArrayList<>()).add(arc);
        inputArcs.computeIfAbsent(transition.getId(), s -> new ArrayList<>()).add(arc);
        return new InputArcBuilder(arc);
    }

    public OutputArcBuilder connect(Place place, Transition transition) {
        var arc = new OutputArc(transition, place);
        outputArcs.computeIfAbsent(place.getId(), s -> new ArrayList<>()).add(arc);
        outputArcs.computeIfAbsent(transition.getId(), s -> new ArrayList<>()).add(arc);
        return new OutputArcBuilder(arc);
    }

    // can proceed only if:
    //  - there is marker in each of input field
    //  - each incoming/outgoing transition is allowed for all markers
    public boolean canProceedWith(Transition transition) {
        return inputArcs
                .getOrDefault(transition.getId(), Collections.emptyList())
                .stream()
                .noneMatch(input -> input.tryConsume().isEmpty());
    }


    // gets markers from incoming states
    // removes them
    // executes action
    // executes post-action
    // mark transition as visited
    // puts markers to outgoing states
    @SneakyThrows
    public Transition proceed(Transition transition) {
        inputArcs
                .getOrDefault(transition.getId(), Collections.emptyList())
                .stream()
                .forEach(InputArc::consume);
        outputArcs
                .getOrDefault(transition.getId(), Collections.emptyList())
                .stream()
                .forEach(output -> output.produce());
        return transition;
    }

    public Stream<Transition> getTransitions() {
        return transitions.values().stream();
    }

    public Stream<Place> getPlaces() {
        return places.values().stream();
    }

    @AllArgsConstructor
    @Getter
    public class InputArcBuilder {
        final private InputArc transition;

        public InputArcBuilder andViceVersa() {
            connect(transition.getTransition(), transition.getPlace());
            return this;
        }
    }

    @AllArgsConstructor
    @Getter
    public class OutputArcBuilder {
        final private OutputArc transition;

        public OutputArcBuilder andViceVersa() {
            connect(transition.getTransition(), transition.getPlace());
            return this;
        }
    }

    @AllArgsConstructor
    @Getter
    public class TransitionBuilder {
        final private Transition transition;

        public TransitionBuilder from(Place... places) {
            for (var place: places) {
                connect(place, transition);
            }
            return this;
        }

        public TransitionBuilder fromAndTo(Place... places) {
            for (var place: places) {
                connect(place, transition);
                connect(transition, place);
            }
            return this;
        }

        public TransitionBuilder to(Place... places) {
            for (var place: places) {
                connect(transition, place);
            }
            return this;
        }

        public TransitionBuilder afterPass(PostTransition action) {
            transition.addPostTransition(action);
            return this;
        }

        public Transition build() {
            return transition;
        }
    }

}
