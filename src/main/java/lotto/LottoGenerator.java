package lotto;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

/**
 * LottoGenerator 클래스는 랜덤한 6개의 로또 번호를 생성하는 역할을 함
 */

public class LottoGenerator {

    public static List<Integer> generate(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return numbers;
    }
}
