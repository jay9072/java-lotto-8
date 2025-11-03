package lotto;

public enum Rank {
    FIRST(6, false, 2_000_000_000L),   // 6개 일치 → 1등
    SECOND(5, true, 30_000_000L),      // 5개 + 보너스 일치 → 2등
    THIRD(5, false, 1_500_000L),       // 5개만 일치 → 3등
    FOURTH(4, false, 50_000L),         // 4개 일치 → 4등
    FIFTH(3, false, 5_000L),           // 3개 일치 → 5등
    NONE(0, false, 0L)                 // 그 외 → 꽝
    ;

    private final int matchCount;      // 이 등수를 만들 최소 일치 개수
    private final boolean needBonus;   // 보너스 포함 조건이 필요한가
    private final long prize;          // 상금

    Rank(int matchCount, boolean needBonus, long prize) {
        this.matchCount = matchCount;
        this.needBonus = needBonus;
        this.prize = prize;
    }

    public long prize() {
        return prize;
    }

    public int matchCount() {
        return matchCount;
    }

    public boolean needBonus() {
        return needBonus;
    }

    /**
     * 일치 개수와 보너스 일치 여부로 등수를 판정
     * switch/else 없이 조기 반환으로 분기
     */
    public static Rank of(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) return FIRST;              // 6개면 바로 1등 반환
        if (matchCount == 5 && bonusMatched) return SECOND; // 5개+보너스면 2등
        if (matchCount == 5) return THIRD;              // 5개만 맞으면 3등
        if (matchCount == 4) return FOURTH;             // 4개면 4등
        if (matchCount == 3) return FIFTH;              // 3개면 5등
        return NONE;                                    // 나머지는 꽝
    }
}
