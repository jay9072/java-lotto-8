package lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;


class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6,false,FIRST",   // 6개 일치 → 1등
            "5,true,SECOND",   // 5개 + 보너스 → 2등
            "5,false,THIRD",   // 5개만 → 3등
            "4,false,FOURTH",  // 4개 → 4등
            "3,false,FIFTH",   // 3개 → 5등
            "2,false,NONE"     // 2개 이하 → 꽝
    })
    void 일치개수와_보너스여부로_등수가_정확히_판정된다(int matchCount, boolean bonusMatched, Rank expected) {
        // when
        Rank result = Rank.of(matchCount, bonusMatched);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "FIRST,2000000000",
            "SECOND,30000000",
            "THIRD,1500000",
            "FOURTH,50000",
            "FIFTH,5000",
            "NONE,0"
    })
    void 각_등수의_상금이_정확하다(Rank rank, long prize) {
        assertThat(rank.prize()).isEqualTo(prize);
    }
}
