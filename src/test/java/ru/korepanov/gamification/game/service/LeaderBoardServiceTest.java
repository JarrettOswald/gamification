package ru.korepanov.gamification.game.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.korepanov.gamification.game.domain.BadgeCard;
import ru.korepanov.gamification.game.domain.BadgeType;
import ru.korepanov.gamification.game.domain.LeaderBoardRow;
import ru.korepanov.gamification.game.domain.ScoreCard;
import ru.korepanov.gamification.game.repository.BadgeRepository;
import ru.korepanov.gamification.game.repository.ScoreRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class LeaderBoardServiceTest {

    @Autowired
    private LeaderBoardService leaderBoardService;

    @MockBean
    private ScoreRepository scoreRepository;

    @MockBean
    private BadgeRepository badgeRepository;

    @BeforeEach
    void setUp() {
        var leaderBoardRowList = List.of(
                new LeaderBoardRow(1L, 10000L),
                new LeaderBoardRow(2L, 6000L)
        );
        when(scoreRepository.findFirst10()).thenReturn(leaderBoardRowList);

        var badgeCardList = List.of(
                new BadgeCard(1L, BadgeType.FIRST_WON),
                new BadgeCard(1L, BadgeType.SILVER)
        );
        when(badgeRepository.findByUserIdOrderByBadgeTimestampDesc(1L)).thenReturn(badgeCardList);

        var scoreCardList = List.of(
                new ScoreCard(1L, 1L)
        );
        when(scoreRepository.findByUserIdOrderByScoreTimestampDesc(1L)).thenReturn(scoreCardList);
    }

    @Test
    void getCurrentLeaderBoardTest() {
        var leaderBoardRowListWithBadgeCard = leaderBoardService.getCurrentLeaderBoard();

        Assertions.assertEquals(2, leaderBoardRowListWithBadgeCard.size());
        Assertions.assertEquals(2, leaderBoardRowListWithBadgeCard.get(0).getBadges().size());
        Assertions.assertEquals(10000L, leaderBoardRowListWithBadgeCard.get(0).getTotalScore());
        Assertions.assertEquals(0, leaderBoardRowListWithBadgeCard.get(1).getBadges().size());
        Assertions.assertEquals(6000L, leaderBoardRowListWithBadgeCard.get(1).getTotalScore());
    }


    @Test
    void currentLeaderBoardDescriptionTest() {
        var leaderBoardRowListWithBadgeCard = leaderBoardService.getCurrentLeaderBoard();
        var badges = leaderBoardRowListWithBadgeCard.get(0).getBadges();

        Assertions.assertEquals(2, badges.size());
        Assertions.assertTrue(badges.contains("First time"));
        Assertions.assertTrue(badges.contains("Silver"));
    }
}