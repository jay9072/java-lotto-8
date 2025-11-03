package lotto;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private Lotto lotto(int... numbers){
        return new Lotto(Arrays.stream(numbers).boxed().toList());
    }

    @Test
    void 로또_결과를_정확히_계산(){

        Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        List<Lotto> lottos = List.of(
                lotto(1, 2, 3, 4, 5, 6),   // 1등
                lotto(1, 2, 3, 4, 5, 7),   // 2등
                lotto(1, 2, 3, 4, 5, 8),   // 3등
                lotto(1, 2, 3, 4, 9, 10),  // 4등
                lotto(1, 2, 3, 11, 12, 13),// 5등
                lotto(1, 2, 14, 15, 16, 17) // 꽝
        );

        // when
        LottoResult result = LottoResult.evaluate(lottos, winningNumbers, bonusNumber);

        // then (각 등수 개수 검증)
        assertThat(result.countOf(Rank.FIRST)).isEqualTo(1);
        assertThat(result.countOf(Rank.SECOND)).isEqualTo(1);
        assertThat(result.countOf(Rank.THIRD)).isEqualTo(1);
        assertThat(result.countOf(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.countOf(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.countOf(Rank.NONE)).isEqualTo(1);
    }

    @Test
    void 총_상금_및_수익률_계산() {
        // given
        Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        List<Lotto> lottos = List.of(
                lotto(1, 2, 3, 4, 5, 6), // 1등
                lotto(1, 2, 3, 4, 5, 7)  // 2등
        );

        // when
        LottoResult result = LottoResult.evaluate(lottos, winningNumbers, bonusNumber);

        // then
        long totalPrize = result.totalPrize();  // 2등 + 1등 합
        assertThat(totalPrize).isEqualTo(2_030_000_000L);

        // 2장 = 2000원 투자
        double yield = result.yieldPercent(2000L);
        assertThat(yield).isGreaterThan(100.0); // 100% 이상이어야 함
    }
}
