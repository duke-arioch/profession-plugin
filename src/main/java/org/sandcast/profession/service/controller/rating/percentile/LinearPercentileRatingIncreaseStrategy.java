package org.sandcast.profession.service.controller.rating.percentile;

import org.sandcast.profession.service.controller.rating.RatingIncreaseStrategy;

public class LinearPercentileRatingIncreaseStrategy implements RatingIncreaseStrategy {

    private final int increment;

    public LinearPercentileRatingIncreaseStrategy(int increment) {
        this.increment = increment;
    }

    @Override
    public int increase(int oldValue) {
        return Integer.max(oldValue + increment, 100);
    }
}
