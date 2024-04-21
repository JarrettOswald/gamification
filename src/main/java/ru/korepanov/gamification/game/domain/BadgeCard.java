package ru.korepanov.gamification.game.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeCard {

    @Id
    @GeneratedValue
    private Long badgeId;

    private Long userId;

    @EqualsAndHashCode.Exclude
    private long badgeTimestamp;

    @Column(columnDefinition = "smallint", name = "badge_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BadgeType badgeType;

    public BadgeCard(final Long userId, final BadgeType badgeType) {
        this(null, userId, System.currentTimeMillis(), badgeType);
    }
}
