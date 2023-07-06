package com.kuba.forum.database.sequences;

import org.springframework.stereotype.Component;

@Component
public class PostSequence implements IPostSequence {
    private int id = 0;

    @Override
    public int getId() {
        return ++id;
    }
}
