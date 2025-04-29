package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.dto.request.FinancialGoalRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.FinancialGoalResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;

import java.util.List;

public interface FinancialGoalService {

    FinancialGoal createGoal(FinancialGoalRequest request);
    List<FinancialGoalResponse> findAllFinancialGoal();
    FinancialGoal findFinancialGoal(String id);
    FinancialGoal updateFinancialGoal(String id, FinancialGoalRequest request);
    void deleteFinancialGoal(String id);
}
