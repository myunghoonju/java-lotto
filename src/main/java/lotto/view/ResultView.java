package lotto.view;

import lotto.common.WinningType;
import lotto.domain.GameWinningResult;

import java.util.Collections;
import java.util.List;

public class ResultView {
    private static final String LOTTO_TICKET_COUNT_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String STATISTICS_TITLE_MESSAGE = "\n당첨 통계\n---------";
    private static final String SECOND_STATISTICS_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String STATISTICS_MESSAGE = "%d개 일치 (%d원)- %d개\n";
    private static final String PROFIT_MESSAGE = "총 수익률은 %.2f입니다.";
    private static final String MINUS_PROFIT_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final int PROFIT_STANDARD = 1;
    private static final int MIN_PROFIT_MATCHING_COUNT = 3;

    public void printLottoTicketCount(int manualTicketCount, int lottoTicketCount) {
        System.out.printf(LOTTO_TICKET_COUNT_MESSAGE, manualTicketCount, lottoTicketCount);
    }

    public void printLottoTickets(String lottoTicketsText) {
        System.out.println(lottoTicketsText);
    }

    public void printGameResult(List<GameWinningResult> gameResult) {
        System.out.println(STATISTICS_TITLE_MESSAGE);
        Collections.reverse(gameResult);
        for(GameWinningResult result : gameResult) {
            printGameResult(result.getWinningType(), result.getWinningCount());
        }
    }

    private void printGameResult(WinningType winningType, int rank) {
        if(winningType == WinningType.SECOND) {
            System.out.printf(SECOND_STATISTICS_MESSAGE, winningType.getMatchCount(), winningType.getPrize(), rank);
        }

        if((winningType != WinningType.SECOND) && (winningType.getMatchCount() >= MIN_PROFIT_MATCHING_COUNT)) {
            System.out.printf(STATISTICS_MESSAGE, winningType.getMatchCount(), winningType.getPrize(), rank);
        }
    }

    public void printResultProfit(double profit) {
        System.out.printf(PROFIT_MESSAGE, profit);
        if(profit < PROFIT_STANDARD) {
            System.out.print(MINUS_PROFIT_MESSAGE);
        }
    }
}
