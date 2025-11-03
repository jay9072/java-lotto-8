package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static int readPurchaseAmount(){
        System.out.println("구입금액을 입력해 주세요 ");
        String input = Console.readLine().trim();
        int amount = parseAmount(input);
        return amount;
    }

    //당첨 번호 입력
    public static String readWinningNumbers(){
        System.out.println("\n당첨 번호를 입력해 주세요.");
        return Console.readLine().trim();
    }

    //보너스 번호 입력
    public static String readBonusNumbers(){
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return Console.readLine().trim();
    }

    //내부 유효성 검사
    private static int parseAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            if (amount <= 0 || amount % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위의 양수여야 합니다.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR 숫자를 입력해야 합니다");
        }
    }
}
