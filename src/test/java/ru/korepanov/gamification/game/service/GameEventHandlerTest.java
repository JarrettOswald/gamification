package ru.korepanov.gamification.game.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import ru.korepanov.gamification.challenge.ChallengeSolvedEvent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GameEventHandlerTest {

    private GameEventHandler gameEventHandler;

    private final ChallengeSolvedEvent challengeSolvedEvent = new ChallengeSolvedEvent(
            1L,
            false,
            1,
            2,
            1L,
            "test");

    @Mock
    private GameService gameService;

    @BeforeEach
    public void setUp() {
        gameEventHandler = new GameEventHandler(gameService);
    }

    @Test()
    void handleMultiplicationSolvedExceptionText() {
        given(gameService.newAttemptForUser(any())).willThrow(new RuntimeException());
        assertThrows(AmqpRejectAndDontRequeueException.class, () -> gameEventHandler.handleMultiplicationSolved(challengeSolvedEvent));
    }
}