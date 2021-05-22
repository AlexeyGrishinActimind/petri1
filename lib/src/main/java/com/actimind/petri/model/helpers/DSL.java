package com.actimind.petri.model.helpers;

import com.actimind.petri.model.DataHolder;

public class DSL {

    public static void markVisited(DataHolder<?> dataHolder) {
        if (dataHolder.get(Visited.class) == null) {
            dataHolder.add(new Visited());
        }
    }

    public static boolean isVisited(DataHolder<?> dataHolder) {
        return dataHolder.get(Visited.class) != null;
    }

}
