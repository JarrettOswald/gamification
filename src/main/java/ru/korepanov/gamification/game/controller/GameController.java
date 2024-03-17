package ru.korepanov.gamification.game.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.korepanov.gamification.challenge.ChallengeSolvedDTO;
import ru.korepanov.gamification.game.service.GameService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attempts")
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postResult(@RequestBody ChallengeSolvedDTO dto) {
        gameService.newAttemptForUser(dto);
    }
}
