package lotto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class LottoGeneratorTest {

    @Test
    void generate_로또번호_6개_생성() {
        List<Integer> numbers = LottoGenerator.generate();

        // 1) 6개가 생성되는지 확인
        assertThat(numbers).hasSize(6);

        // 2) 범위 1~45 안에 모두 있는지 확인
        assertThat(numbers).allMatch(num -> num >= 1 && num <= 45);

        // 3) 중복이 없는지 확인
        long distinctCount = numbers.stream().distinct().count();
        assertThat(distinctCount).isEqualTo(6);
    }
}
