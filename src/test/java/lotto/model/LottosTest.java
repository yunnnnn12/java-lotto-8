package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    void 로또_목록이_정상적으로_저장된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(lotto));

        assertThat(lottos.getLottoList()).hasSize(1);
        assertThat(lottos.getLottoList().get(0).getNumbers())
                .containsExactly(1, 2, 3, 4, 5, 6);
    }
}
