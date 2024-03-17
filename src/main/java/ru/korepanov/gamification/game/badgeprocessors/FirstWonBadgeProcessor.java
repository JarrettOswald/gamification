package ru.korepanov.gamification.game.badgeprocessors;

import org.springframework.stereotype.Component;
import ru.korepanov.gamification.challenge.ChallengeSolvedDTO;
import ru.korepanov.gamification.game.domain.ScoreCard;
import ru.korepanov.gamification.game.domain.BadgeType;

import java.util.List;
import java.util.Optional;

@Component
public class FirstWonBadgeProcessor implements BadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved) {
        return scoreCardList.size() == 1 ? Optional.of(BadgeType.FIRST_WON) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.FIRST_WON;
    }
}
