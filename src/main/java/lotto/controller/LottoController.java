// src/main/java/lotto/controller/LottoController.java
package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int totalMoney = inputView.returnTotalMoney();
        int count = totalMoney / 1000;

        // 테스트용 고정 번호 로또
        List<List<Integer>> fixedLottos = List.of(
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );

        // 테스트용 큐 세팅 → 로또 생성
        lottoService.setTestRandomNumbers(fixedLottos);
        lottoService.createLottos(count);

        // 생성된 로또 출력
        outputView.printPurchasedLottos(lottoService.getLottos());

        // 당첨번호 입력
        List<Integer> winNums = inputView.returnWinNumList();
        int bonus = inputView.returnBonusNum(winNums);

        // 결과 계산 후 출력
        lottoService.compareLotto(winNums, bonus);
        outputView.printResult(totalMoney, lottoService);
    }

    // 테스트용 고정 로또 생성 및 실행
    public void runWithFixedLotto(int totalMoney, List<Integer> winNums, int bonus, List<List<Integer>> fixedLottos) {
        lottoService.createFixedLottos(fixedLottos);
        outputView.printPurchasedLottos(fixedLottos);
        lottoService.compareLotto(winNums, bonus);
        outputView.printResult(totalMoney, lottoService);
    }
}
