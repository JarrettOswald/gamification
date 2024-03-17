package ru.korepanov.gamification.game.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korepanov.gamification.game.domain.LeaderBoardRow;
import ru.korepanov.gamification.game.repository.BadgeRepository;
import ru.korepanov.gamification.game.repository.ScoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;


    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        List<LeaderBoardRow> scoreOnly = scoreRepository.findFirst10();
        return scoreOnly.stream().map(row -> {
            List<String> badges =
                    badgeRepository.findByUserIdOrderByBadgeTimestampDesc(row.getUserId())
                            .stream()
                            .map(b -> b.getBadgeType().getDescription())
                            .collect(Collectors.toList());
            return row.withBadges(badges);
        }).collect(Collectors.toList());
    }
}
