package ru.korepanov.gamification.game.service;

import ru.korepanov.gamification.game.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {

    List<LeaderBoardRow> getCurrentLeaderBoard();
}
