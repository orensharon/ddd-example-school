package com.atlantium.aio.reactor.domain;

import com.atlantium.aio.reactor.util.eda.framework.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class Aggregate {

    private final List<Event> events;
    private boolean synced;

    public abstract void rollback(int unitId);

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

    public void synced() {
        if (!this.synced) {
            // TODO: noam - enqueue?
        }
        this.synced = true;
    }

    public void unSynced() {
        if (this.synced) {
            // TODO: noam - enqueue?
        }
        this.synced = false;
    }

    public boolean isSynced() {
        return synced;
    }
}
