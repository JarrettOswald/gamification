package ru.korepanov.gamification.game.badgeprocessors;

import ru.korepanov.gamification.challenge.ChallengeSolvedDTO;
import ru.korepanov.gamification.game.domain.ScoreCard;
import ru.korepanov.gamification.game.domain.BadgeType;

import java.util.List;
import java.util.Optional;

public interface BadgeProcessor {

    Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved);

    BadgeType badgeType();
}
