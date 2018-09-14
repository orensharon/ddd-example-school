package com.sharono.example.ddd.school.domain;


import com.sharono.example.ddd.school.util.eda.framework.Event;

import java.util.ArrayList;
import java.util.List;

public class Aggregate {

    private final List<Event> events;

    public Aggregate() {
        this.events = new ArrayList<>();
    }

    protected void enqueue(Event event) {
        this.events.add(event);
    }

    public Event[] dequeue() {
        Event[] result = this.events.toArray(new Event[this.events.size()]);
        this.events.clear();
        return result;
    }
}
