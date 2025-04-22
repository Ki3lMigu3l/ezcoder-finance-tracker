package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import br.dev.ezcoder.ezfinancepro.repositories.FinancialGoalRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinancialGoalService {

    private final FinancialGoalRepository financialGoalRepository;

    public FinancialGoal createGoal(FinancialGoal bankAccount) {
        return financialGoalRepository.save(bankAccount);
    }

    public List<FinancialGoal> findAll() {
        return financialGoalRepository.findAll();
    }

    public Optional<FinancialGoal> findById(String id) {
        return financialGoalRepository.findById(id);
    }

    public FinancialGoal update(@Valid FinancialGoal request) {
        return financialGoalRepository.save(request);
    }

    public void delete(String id) {
        financialGoalRepository.deleteById(id);
    }
}
