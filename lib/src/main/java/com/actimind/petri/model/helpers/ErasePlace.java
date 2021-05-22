package com.actimind.petri.model.helpers;

import com.actimind.petri.model.Network;
import com.actimind.petri.model.PostTransition;
import com.actimind.petri.model.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class ErasePlace implements PostTransition {

    @NonNull  private Place place;

    @Override
    public void execute(Network network) {
        this.place.removeAll();
    }
}
