package ru.korepanov.gamification.game.badgeprocessors;

import org.springframework.stereotype.Component;
import ru.korepanov.gamification.challenge.ChallengeSolvedEvent;
import ru.korepanov.gamification.game.domain.BadgeType;
import ru.korepanov.gamification.game.domain.ScoreCard;

import java.util.List;
import java.util.Optional;

@Component
public class SilverBadgeProcessor implements BadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedEvent solved) {
        return currentScore > 150 ? Optional.of(BadgeType.SILVER) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.SILVER;
    }
}
