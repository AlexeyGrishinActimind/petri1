package com.actimind.petri;

import com.actimind.petri.model.Network;
import com.actimind.petri.model.Place;
import com.actimind.petri.model.Transition;
import com.actimind.petri.model.helpers.ErasePlace;

public class ExampleNetwork extends Network {

    public Place userNotCreated = place("user not created");
    public Place userCreated = place("user created");
    public Place noteCreated = place("note created");

    public Transition createUser = transition("create user")
            .from(userNotCreated)
            .to(userCreated)
            .build();
    public Transition destroyUser = transition("destroy user")
            .from(userCreated)
            .to(userNotCreated)
            .afterPass(new ErasePlace(noteCreated))
            .build();
    public Transition createNote = transition("create note")
            .fromAndTo(userCreated)
            .to(noteCreated)
            .build();
    public Transition destroyNote = transition("destroy note")
            .from(noteCreated)
            .build();

}
