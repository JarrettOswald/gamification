package ru.korepanov.gamification.game.badgeprocessors;

import org.springframework.stereotype.Component;
import ru.korepanov.gamification.challenge.ChallengeSolvedDTO;
import ru.korepanov.gamification.game.domain.ScoreCard;
import ru.korepanov.gamification.game.domain.BadgeType;

import java.util.List;
import java.util.Optional;

@Component
public class LuckyNumberProcessor implements BadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved) {
        return (solved.getFactorA() == 42 || solved.getFactorB() == 42) ? Optional.of(BadgeType.LUCKY_NUMBER) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.LUCKY_NUMBER;
    }
}