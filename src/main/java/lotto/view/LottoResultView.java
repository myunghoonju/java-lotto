package lotto.view;

import lotto.domain.LottoEarningCalculator;
import lotto.domain.LottoPrize;
import lotto.domain.LottoTicket;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultView {

    public static final String RESULT_OUTPUT_COMMENT = "당첨 통계\n---------";

    public static void printTickets(List<LottoTicket> ticketList) {
        System.out.println(ticketList.size() + "개를 구매했습니다.");
        ticketList.stream()
                .map(LottoTicketView::new)
                .forEach(System.out::println);
    }

    public static void printResult(List<LottoPrize> prizeList, int numberOfTickets) {
        System.out.println(RESULT_OUTPUT_COMMENT);

        List<LottoPrize> ascendingOrderedLottoPrizeList = Arrays.stream(LottoPrize.values())
                .sorted(Comparator.comparingInt(LottoPrize::getReward))
                .collect(Collectors.toList());
        Map<LottoPrize, Long> prizeCountMap = prizeList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        printLottoPrizes(ascendingOrderedLottoPrizeList, prizeCountMap);
        printLottoEarningRate(prizeList, numberOfTickets);
    }

    private static void printLottoPrizes(final List<LottoPrize> ascendingOrderedLottoPrizeList, final Map<LottoPrize, Long> prizeCountingMap) {
        for (LottoPrize prize : ascendingOrderedLottoPrizeList) {
            String prizeInfo = prize.getCommonNumberCount() + "개 일치 (" + prize.getReward() + ")";
            long prizeCount = prizeCountingMap.getOrDefault(prize, 0L);

            System.out.println(prizeInfo + " - " + prizeCount + "개");
        }
    }

    private static void printLottoEarningRate(final List<LottoPrize> prizeList, final int numberOfTickets) {
        float earningRate = LottoEarningCalculator.calculateEarningRate(prizeList, numberOfTickets);
        System.out.println("총 수익률은 " + earningRate + "입니다.");
    }
}
