package org.sandcast.profession.service.controller.rating.percentile;

import java.util.Random;
import org.sandcast.profession.service.model.Experience;
import org.sandcast.profession.service.controller.rating.RatingEligibilityStrategy;

public class PercentileRatingIncreaseEligibilityStrategy implements RatingEligibilityStrategy {

    int percentilePlaces = 2;
    Random random = new Random();

    public PercentileRatingIncreaseEligibilityStrategy(int percentileDecimals) {
        this.percentilePlaces = percentileDecimals;
    }

    @Override
    public boolean isElligible(Experience e) {
        double percentileChance = e.getRating() / (double) (10 ^ percentilePlaces);
        double roll = random.nextDouble();
        return (roll > percentileChance);
    }
}
