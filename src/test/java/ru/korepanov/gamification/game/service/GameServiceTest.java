package ru.korepanov.gamification.game.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.korepanov.gamification.challenge.ChallengeSolvedEvent;
import ru.korepanov.gamification.game.domain.BadgeType;
import ru.korepanov.gamification.game.repository.BadgeRepository;
import ru.korepanov.gamification.game.repository.ScoreRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class GameServiceTest {
    @Autowired
    private GameServiceImpl gameService;
    @MockBean
    private ScoreRepository scoreRepository;
    @MockBean
    private BadgeRepository badgeRepository;

    @Test
    void newAttemptForUserTest() {
        ChallengeSolvedEvent challengeIsCorrect = new ChallengeSolvedEvent(
                10L,
                true,
                41,
                41,
                200L,
                "Ivan"
        );
        when(scoreRepository.getTotalScoreForUser(200L)).thenReturn(Optional.of(20));

        var gameResult = gameService.newAttemptForUser(challengeIsCorrect);
        log.info(gameResult.toString());

        Assertions.assertEquals(10, gameResult.getScore());
        Assertions.assertEquals(0, gameResult.getBadges().size());
    }

    @Test
    void challengeIsNotCorrectTest() {
        ChallengeSolvedEvent challengeNotCorrect = new ChallengeSolvedEvent(
                8L,
                false,
                42,
                42,
                500L,
                "Goofy"
        );
        var gameResult = gameService.newAttemptForUser(challengeNotCorrect);
        Assertions.assertEquals(0, gameResult.getScore());
        Assertions.assertEquals(0, gameResult.getBadges().size());
    }

    @Test
    void badgeProcessorTest() {
        when(scoreRepository.getTotalScoreForUser(201L)).thenReturn(Optional.of(401));
        ChallengeSolvedEvent challengeWithGoldBadge = new ChallengeSolvedEvent(
                4L,
                true,
                42,
                40,
                201L,
                "Goofy"
        );

        var gameResult = gameService.newAttemptForUser(challengeWithGoldBadge);

        Assertions.assertEquals(4, gameResult.getBadges().size());
        Assertions.assertTrue(gameResult.getBadges().contains(BadgeType.GOLD));
        Assertions.assertTrue(gameResult.getBadges().contains(BadgeType.SILVER));
        Assertions.assertTrue(gameResult.getBadges().contains(BadgeType.BRONZE));
        Assertions.assertTrue(gameResult.getBadges().contains(BadgeType.LUCKY_NUMBER));
    }

    @Test
    void badgeProcessorOptTotalScoreIsEmptyTest() {
        when(scoreRepository.getTotalScoreForUser(201L)).thenReturn(Optional.empty());
        ChallengeSolvedEvent challengeWithGoldBadge = new ChallengeSolvedEvent(
                4L,
                true,
                42,
                40,
                201L,
                "Goofy"
        );

        var gameResult = gameService.newAttemptForUser(challengeWithGoldBadge);

        Assertions.assertEquals(0, gameResult.getBadges().size());
    }
}