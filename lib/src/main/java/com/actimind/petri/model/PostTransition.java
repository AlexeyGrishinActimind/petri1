package com.actimind.petri.model;

@FunctionalInterface
public interface PostTransition {

    void execute(Network network);
}
