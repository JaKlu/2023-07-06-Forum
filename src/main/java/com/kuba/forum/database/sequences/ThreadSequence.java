package com.kuba.forum.database.sequences;
import org.springframework.stereotype.Component;

@Component
public class ThreadSequence implements IThreadSequence{
    private int id = 0;

    @Override
    public int getId() {
        return ++id;
    }
}
