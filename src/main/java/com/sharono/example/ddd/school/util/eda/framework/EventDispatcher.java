package com.sharono.example.ddd.school.util.eda.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles the routing of {@link Event} messages to associated handlers.
 * A {@link HashMap} is used to store the association between events and their respective handlers.
 */
public class EventDispatcher {

    private Map<Class<? extends Event>, List<Handler<? extends Event>>> handlers;

    public EventDispatcher() {
        handlers = new HashMap<>();
    }

    /**
     * Links an {@link Event} to a specific {@link Handler}.
     *
     * @param eventType The {@link Event} to be registered
     * @param handler   The {@link Handler} that will be handling the {@link Event}
     */
    public <E extends Event> void registerHandler(Class<E> eventType,
                                                  Handler<E> handler) {
        if (handlers.get(eventType) == null) {
            handlers.put(eventType, new ArrayList<>());
        }
        handlers.get(eventType).add(handler);
        //handlers.put(eventType, handler);
    }

    /**
     * Dispatches an {@link Event} depending on it's type.
     *
     * @param event The {@link Event} to be dispatched
     */
    @SuppressWarnings("unchecked")
    public <E extends Event> void dispatch(E event) {
        List<Handler<? extends Event>> handlers =  this.handlers.get(event.getClass());
        if (handlers != null) {
            for (Handler handler : handlers) {
                handler.onEvent(event);
            }
        }
    }

    public void clear() {
        this.handlers.clear();
    }
}
