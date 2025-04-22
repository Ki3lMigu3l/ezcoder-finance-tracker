package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import br.dev.ezcoder.ezfinancepro.service.FinancialGoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/financial/goal")
@RequiredArgsConstructor
public class FinancialGoalController {

    private final FinancialGoalService financialGoalService;

    @PostMapping
    public ResponseEntity<FinancialGoal> registrerBankAccount (@RequestBody FinancialGoal request) {
        var bankAccount = new FinancialGoal();
        BeanUtils.copyProperties(request, bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(financialGoalService.createGoal(bankAccount));
    }

    @GetMapping
    public ResponseEntity<List<FinancialGoal>> getAllCategories () {
        return ResponseEntity.status(HttpStatus.OK).body(financialGoalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialGoal> getCategory (@PathVariable String id) {
        FinancialGoal financialGoalFound = financialGoalService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Financial Goal not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(financialGoalFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialGoal> updateTransaction (@PathVariable String id,
                                                          @RequestBody @Valid FinancialGoal request) {

        FinancialGoal financialGoalFound = financialGoalService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Financial Goal not found!"));

        request.setId(financialGoalFound.getId());
        return ResponseEntity.status(HttpStatus.OK).body(financialGoalService.update(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction (@PathVariable String id) {

        FinancialGoal financialGoalFound = financialGoalService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Financial Goal not found!"));

        financialGoalService.delete(id);
    }
}
