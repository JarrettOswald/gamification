package ru.korepanov.gamification.game.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korepanov.gamification.game.domain.LeaderBoardRow;
import ru.korepanov.gamification.game.service.LeaderBoardService;

import java.util.List;

@RestController
@RequestMapping("/leaders")
@RequiredArgsConstructor
@Slf4j
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {
        log.info("Retrieving leaderboard");
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
