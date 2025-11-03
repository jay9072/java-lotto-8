package lotto;

import java.util.List;


public class OutputView {

    // 구매한 로또 목록 출력
    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers()); // Lotto의 번호 리스트 출력
        }
        System.out.println();
    }

    // 당첨 결과 출력
    public static void printResult(List<String> resultLines, double rate) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (String line : resultLines) {
            System.out.println(line);
        }
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }

    // 에러 메시지 출력
    public static void printError(String message) {
        System.out.println(message);
    }
}
