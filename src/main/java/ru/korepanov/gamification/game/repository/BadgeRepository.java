package ru.korepanov.gamification.game.repository;

import org.springframework.data.repository.CrudRepository;
import ru.korepanov.gamification.game.domain.BadgeCard;

import java.util.List;

public interface BadgeRepository extends CrudRepository<BadgeCard, Long> {

    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
