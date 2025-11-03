package lotto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

class LottoStoreTest {

    @Test
    void 금액에_맞게_로또를_발행한다() {
        // given
        int amount = 5000;

        // when
        List<Lotto> lottos = LottoStore.buyLottos(amount);

        // then
        assertThat(lottos).hasSize(5); // 5000원이면 5장 발행되어야 함
    }

    @Test
    void 금액이_1000원_단위가_아니면_예외_발생() {
        // given
        int wrongAmount = 550;

        // when & then
        assertThatThrownBy(() -> LottoStore.buyLottos(wrongAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
