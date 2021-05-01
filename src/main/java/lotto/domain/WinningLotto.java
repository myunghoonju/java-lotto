package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static lotto.exception.Message.BONUS_DUPLICATION_MESSAGE;

public class WinningLotto {

    private final LottoBalls lottoBalls;
    private final LottoBall bonusBall;

    public WinningLotto(LottoBalls lottoBalls, LottoBall bonusBall) {
        validate(lottoBalls, bonusBall);
        this.lottoBalls = lottoBalls;
        this.bonusBall = bonusBall;
    }

    public static WinningLotto of(int bonusBall, Integer... winningBalls) {
        return WinningLotto.of(Arrays.asList(winningBalls), bonusBall);
    }

    public static WinningLotto of(List<Integer> winningBalls, int bonusBall) {
        return new WinningLotto(LottoBalls.createWinningLottoBalls(winningBalls), LottoBall.valueOf(bonusBall));
    }

    private static void validate(LottoBalls winningBalls, LottoBall bonusBall) {
        if (winningBalls.getSortedLottoBalls().contains(bonusBall)) {
            throw new IllegalArgumentException(BONUS_DUPLICATION_MESSAGE);
        }
    }

    public LottoBalls getWinningLottoBalls() {
        return lottoBalls;
    }

    public LottoBall getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lottoBalls, that.lottoBalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoBalls);
    }
}