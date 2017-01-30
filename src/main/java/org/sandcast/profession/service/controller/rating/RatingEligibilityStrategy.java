package org.sandcast.profession.service.controller.rating;

import org.sandcast.profession.service.model.Experience;

public interface RatingEligibilityStrategy {

    public boolean isElligible(Experience experience);
}
