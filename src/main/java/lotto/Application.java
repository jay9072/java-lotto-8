package lotto;

import java.util.*;
import camp.nextstep.edu.missionutils.Console;

/**
 * 프로그램의 진입점
 * 전체 실행 흐름:
 * 1) 구입 금액 입력 → 로또 발행
 * 2) 발행된 로또 출력
 * 3) 당첨 번호 및 보너스 입력
 * 4) 결과 계산 및 출력
 */
public class Application {
    public static void main(String[] args) {
        try {
            // 1. 구입 금액 입력
            int purchaseAmount = InputView.readPurchaseAmount();

            // 2. 로또 구매 및 출력
            List<Lotto> lottos = LottoStore.buyLottos(purchaseAmount);
            OutputView.printLottos(lottos);

            // 3. 당첨 번호 입력
            String winningInput = InputView.readWinningNumbers();
            Set<Integer> winningNumbers = parseWinningNumbers(winningInput);

            // 4. 보너스 번호 입력
            int bonusNumber = Integer.parseInt(InputView.readBonusNumber());

            // 5. 결과 계산
            LottoResult result = LottoResult.evaluate(lottos, winningNumbers, bonusNumber);

            // 6. 출력
            OutputView.printResult(result.toDisplayLines(), result.yieldPercent(purchaseAmount));

            Console.close();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            main(args); // 예외 발생 시 처음부터 다시 입력받기
        }
    }

    /**
     * 쉼표로 구분된 문자열을 Set<Integer>로 변환
     */
    private static Set<Integer> parseWinningNumbers(String input) {
        Set<Integer> numbers = new HashSet<>();
        String[] tokens = input.split(",");
        for (String token : tokens) {
            int number = Integer.parseInt(token.trim());
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
            }
            if (!numbers.add(number)) {
                throw new IllegalArgumentException("[ERROR] 중복된 번호가 있습니다.");
            }
        }
        return numbers;
    }
}
