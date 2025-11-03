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

        lottoService.createLottos(count);
        outputView.printPurchasedLottos(lottoService.getLottos());

        List<Integer> winNums = inputView.returnWinNumList();
        int bonus = inputView.returnBonusNum(winNums);

        lottoService.compareLotto(winNums, bonus);
        outputView.printResult(totalMoney, lottoService);
    }


    public void runWithFixedLotto(int totalMoney, List<Integer> winNums, int bonus, List<List<Integer>> fixedLottos) {
        lottoService.createFixedLottos(fixedLottos);
        outputView.printPurchasedLottos(fixedLottos);
        lottoService.compareLotto(winNums, bonus);
        outputView.printResult(totalMoney, lottoService);
    }
}
