package lotto.service;

import lotto.model.Lotto;
import lotto.model.Lottos;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    void 랜덤_로또_번호는_6개이고_중복이_없으며_범위_내에_있어야한다() {
        List<Integer> numbers = lottoService.RandLottoNums(); // ✅ 이제 LottoService에 추가한 메서드 사용

        assertThat(numbers).hasSize(6);
        assertThat(numbers).doesNotHaveDuplicates();
        assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
    }

    @Test
    void 생성된_로또는_정렬되어야한다() {
        Lottos lottos = lottoService.createLottos(3);

        for (Lotto lotto : lottos.getLottoList()) {
            assertThat(lotto.getNumbers()).isSorted();
        }
    }
}
