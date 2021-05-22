package com.actimind.petri.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ToString
@AllArgsConstructor()
public class Place extends DataHolder<Place> {
    @Getter @NonNull private final String id;
    @Getter @NonNull private final String title;

    private final List<Token> tokens = new ArrayList<>();

    public Place add(Token token) {
        this.tokens.add(token);
        return this;
    }

    public Place remove(Token token) {
        this.tokens.remove(token);
        return this;
    }

    public Place addAll(List<Token> tokens) {
        this.tokens.addAll(tokens);
        return this;
    }

    public Place removeAll(List<Token> tokens) {
        this.tokens.removeAll(tokens);
        return this;
    }

    public Place removeAll() {
        this.tokens.clear();
        return this;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
