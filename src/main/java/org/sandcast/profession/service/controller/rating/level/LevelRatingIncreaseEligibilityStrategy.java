package org.sandcast.profession.service.controller.rating.level;

import java.util.List;
import org.sandcast.profession.service.model.Experience;
import org.sandcast.profession.service.controller.rating.RatingEligibilityStrategy;

public class LevelRatingIncreaseEligibilityStrategy implements RatingEligibilityStrategy {

    final List<Integer> triesPerLevel;
    final int defaultExtraTriesNeeded;
    final boolean successesOnly;

    public LevelRatingIncreaseEligibilityStrategy(List<Integer> triesPerLevel, int highJump, boolean successesOnly) {
        this.triesPerLevel = triesPerLevel;
        this.defaultExtraTriesNeeded = highJump;
        this.successesOnly = successesOnly;
    }

    @Override
    public boolean isElligible(Experience e) {
        final int triesNeeded;
        final int measure;
        if (e.getRating() < triesPerLevel.size()) {
            triesNeeded = triesPerLevel.get(e.getRating() - 1);
        } else {
            triesNeeded = defaultExtraTriesNeeded * (e.getRating() - triesPerLevel.size());
        }
        measure = successesOnly ? e.getSuccesses() : e.getTries();
        return measure >= triesNeeded;
    }
}
