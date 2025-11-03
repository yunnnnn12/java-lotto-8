package lotto.view;

import lotto.model.Lottos;
import lotto.model.Lotto;
import lotto.domain.Rank;
import lotto.service.LottoService;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printPurchasedLottos(List<List<Integer>> fixedLottos) {
        System.out.println(fixedLottos.size() + "개를 구매했습니다.");
        for (List<Integer> lotto : fixedLottos) {
            System.out.println(lotto);
        }
    }

    public void printPurchasedLottos(Lottos lottos) {
        List<Lotto> lottoList = lottos.getLottoList();
        System.out.println(lottoList.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottoList) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printResult(int totalMoney, LottoService lottoService) {
        Map<Rank, Integer> result = lottoService.getResultMap();

        System.out.println("3개 일치 (5,000원) - " + result.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.get(Rank.FIRST) + "개");

        double rate = lottoService.calculateRate(totalMoney);
        System.out.println("총 수익률은 " + String.format("%.1f", rate) + "%입니다.");
    }
}
