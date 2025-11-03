package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    /**
     * LottoStore 클래스는 사용자가 입력한 금액에 따라
     * 여러 장의 로또를 발행하는 역할을 담당한다
     */

    private static final int LOTTO_PRICE = 1000;

    public static List<Lotto> buyLottos(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위어야 합니다.");
        }

        int count = amount / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i <count; i++) {
            lottos.add(new Lotto(LottoGenerator.generate()));
        }
        return lottos;
    }
}
