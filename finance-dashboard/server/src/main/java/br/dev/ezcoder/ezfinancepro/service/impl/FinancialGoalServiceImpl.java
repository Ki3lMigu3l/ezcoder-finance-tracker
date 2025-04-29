package br.dev.ezcoder.ezfinancepro.service.impl;

import br.dev.ezcoder.ezfinancepro.exception.FinancialGoalNotFoundException;
import br.dev.ezcoder.ezfinancepro.model.dto.request.FinancialGoalRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.FinancialGoalResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import br.dev.ezcoder.ezfinancepro.repository.FinancialGoalRepository;
import br.dev.ezcoder.ezfinancepro.service.FinancialGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FinancialGoalServiceImpl implements FinancialGoalService {

    private final FinancialGoalRepository financialGoalRepository;

    @Override
    public FinancialGoal createGoal(FinancialGoalRequest request) {
        return financialGoalRepository.save(new FinancialGoal(request));
    }

    @Override
    public List<FinancialGoalResponse> findAllFinancialGoal() {
        return mapFinancialGoalToResponseDTOs(financialGoalRepository.findAll());
    }

    @Override
    public FinancialGoal findFinancialGoal(String id) {
        return financialGoalRepository.findById(id)
                .orElseThrow(() -> new FinancialGoalNotFoundException(id));
    }

    @Override
    public FinancialGoal updateFinancialGoal(String id, FinancialGoalRequest request) {
        FinancialGoal financialGoalFound = findFinancialGoal(id);
        BeanUtils.copyProperties(request, financialGoalFound);
        return financialGoalRepository.save(financialGoalFound);
    }

    @Override
    public void deleteFinancialGoal(String id) {
        FinancialGoal financialGoal = findFinancialGoal(id);
        financialGoalRepository.delete(financialGoal);
    }

    public List<FinancialGoalResponse> mapFinancialGoalToResponseDTOs (List<FinancialGoal> financialGoals) {
        Objects.requireNonNull(financialGoals, "Financial goals List cannot be Null");

        return financialGoals.stream()
                .filter(Objects::nonNull)
                .map(this::entityToResponseDTO)
                .toList();
    }

    private FinancialGoalResponse entityToResponseDTO(FinancialGoal financialGoal) {
        return FinancialGoalResponse.entityToResponse(financialGoal);
    }

}
