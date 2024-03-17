package ru.korepanov.gamification.game.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.korepanov.gamification.challenge.ChallengeSolvedDTO;

@SpringBootTest
class GameServiceTest {


    @Autowired
    private GameServiceImpl gameService;


    @Test
    void newAttemptForUserTest() {
        ChallengeSolvedDTO challenge = new ChallengeSolvedDTO(
                1L,
                true
                , 42,
                42,
                1L,
                "Ivan"
        );
        var gameResult = gameService.newAttemptForUser(challenge);
        Assertions.assertEquals(10, gameResult.getScore());
    }

    @Test
    void challengeIsCorrectTest() {
        ChallengeSolvedDTO challenge = new ChallengeSolvedDTO(
                1L,
                false
                , 42,
                42,
                1L,
                "Ivan"
        );
        var gameResult = gameService.newAttemptForUser(challenge);
        Assertions.assertEquals(0, gameResult.getScore());
    }
}