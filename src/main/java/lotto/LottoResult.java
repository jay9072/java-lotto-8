package lotto;

import java.util.*;

public class LottoResult {

    private final EnumMap<Rank, Integer> resultMap = new EnumMap<>(Rank.class);

    public LottoResult() {
        // 모든 등수를 0으로 초기화
        for (Rank rank : Rank.values()) {
            resultMap.put(rank, 0);
        }
    }

    /**
     * 로또 리스트와 당첨 정보를 입력받아 결과를 계산
     */
    public static LottoResult evaluate(List<Lotto> lottos, Set<Integer> winningNumbers, int bonusNumber) {
        LottoResult result = new LottoResult();

        for (Lotto lotto : lottos) {
            int matchCount = countMatch(lotto.getNumbers(), winningNumbers);
            boolean bonusMatched = lotto.getNumbers().contains(bonusNumber);

            Rank rank = Rank.of(matchCount, bonusMatched);
            result.increment(rank);
        }
        return result;
    }

    /**
     * 두 리스트 간 일치하는 숫자의 개수를 계산
     */
    private static int countMatch(List<Integer> lottoNumbers, Set<Integer> winningNumbers) {
        int count = 0;
        for (int number : lottoNumbers) {
            if (winningNumbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 특정 등수의 개수를 1 증가
     */
    private void increment(Rank rank) {
        resultMap.put(rank, resultMap.get(rank) + 1);
    }

    /**
     * 특정 등수의 개수를 반환
     */
    public int countOf(Rank rank) {
        return resultMap.get(rank);
    }

    /**
     * 총 상금 합계를 계산한다.
     */
    public long totalPrize() {
        long total = 0L;
        for (Rank rank : Rank.values()) {
            total += rank.prize() * resultMap.get(rank);
        }
        return total;
    }

    /**
     * 수익률을 계산한다. (소수점 한 자리 반올림)
     */
    public double yieldPercent(long purchaseAmount) {
        if (purchaseAmount == 0) return 0.0;
        double ratio = (double) totalPrize() / purchaseAmount * 100.0;
        return Math.round(ratio * 10) / 10.0;
    }

    /**
     * 등수별 결과를 출력용 문자열로 변환한다.
     */
    public List<String> toDisplayLines() {
        List<String> lines = new ArrayList<>();
        lines.add("3개 일치 (5,000원) - " + countOf(Rank.FIFTH) + "개");
        lines.add("4개 일치 (50,000원) - " + countOf(Rank.FOURTH) + "개");
        lines.add("5개 일치 (1,500,000원) - " + countOf(Rank.THIRD) + "개");
        lines.add("5개 일치, 보너스 볼 일치 (30,000,000원) - " + countOf(Rank.SECOND) + "개");
        lines.add("6개 일치 (2,000,000,000원) - " + countOf(Rank.FIRST) + "개");
        return lines;
    }
}
