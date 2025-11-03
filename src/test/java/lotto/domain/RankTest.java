package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("6개 맞으면 FIRST")
    @Test
    void sixMatchIsFirst() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 + 보너스 맞으면 SECOND")
    @Test
    void fivePlusBonusIsSecond() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개만 맞으면 THIRD")
    @Test
    void fiveOnlyIsThird() {
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개 맞으면 FOURTH")
    @Test
    void fourMatchIsFourth() {
        assertThat(Rank.valueOf(4, false)).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개 맞으면 FIFTH")
    @Test
    void threeMatchIsFifth() {
        assertThat(Rank.valueOf(3, false)).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("2개 이하 맞으면 NONE")
    @Test
    void lessThanThreeIsNone() {
        assertThat(Rank.valueOf(2, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.valueOf(0, false)).isEqualTo(Rank.NONE);
    }
}
