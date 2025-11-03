package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean needBonus;
    private final int prize;

    Rank(int matchCount, boolean needBonus, int prize) {
        this.matchCount = matchCount;
        this.needBonus = needBonus;
        this.prize = prize;
    }

    public int prize() {
        return prize;
    }

    public static Rank of(int matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
                .filter(r -> r.matchCount == matchCount)
                .filter(r -> !r.needBonus || bonusMatched)
                .filter(r -> r != SECOND || bonusMatched) // 5개일 때 보너스 필요
                .findFirst()
                .orElse(NONE);
    }
}
