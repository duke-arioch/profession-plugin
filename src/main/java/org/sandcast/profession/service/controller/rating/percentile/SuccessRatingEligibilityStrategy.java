package org.sandcast.profession.service.controller.rating.percentile;

import org.sandcast.profession.service.model.Experience;
import org.sandcast.profession.service.controller.rating.RatingEligibilityStrategy;

public class SuccessRatingEligibilityStrategy implements RatingEligibilityStrategy {

    @Override
    public boolean isElligible(Experience e) {
        return true;
    }
}
