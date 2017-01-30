package org.sandcast.profession.plugin.action;

import org.sandcast.profession.service.model.SkilledPlayer;

public interface Skill<T> {

    boolean appliesTo(T event);

    void apply(T event, SkilledPlayer player);

    int getPriority();

    String getDescription();

    String getId();
}
