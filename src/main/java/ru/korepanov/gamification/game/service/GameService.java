package ru.korepanov.gamification.game.service;

import lombok.Value;
import ru.korepanov.gamification.challenge.ChallengeSolvedEvent;
import ru.korepanov.gamification.game.domain.BadgeType;

import java.util.List;

public interface GameService {

    GameResult newAttemptForUser(ChallengeSolvedEvent challenge);

    @Value
    class GameResult {
        int score;
        List<BadgeType> badges;
    }
}
