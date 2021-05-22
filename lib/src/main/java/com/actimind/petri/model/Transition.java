package com.actimind.petri.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor()
public class Transition extends DataHolder<Transition> {
    @NonNull @Getter final private String id;
    @NonNull @Getter final private String title;

    private final List<PostTransition> postTransitions = new ArrayList<>();

    public void addPostTransition(PostTransition action)
    {
        postTransitions.add(action);
    }

    //todo: readonly
    public Iterable<PostTransition> getPostActions()
    {
        return postTransitions;
    }
}
