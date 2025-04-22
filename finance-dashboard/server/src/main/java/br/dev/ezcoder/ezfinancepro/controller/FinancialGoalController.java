package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import br.dev.ezcoder.ezfinancepro.model.entity.User;
import br.dev.ezcoder.ezfinancepro.service.FinancialGoalService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/financialgoal")
@RequiredArgsConstructor
public class FinancialGoalController {

    private final FinancialGoalService financialGoalService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<FinancialGoal> registrerBankAccount (@RequestBody @Valid FinancialGoal request) {
        User userFound = userService.findUserById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        var bankAccount = new FinancialGoal();
        BeanUtils.copyProperties(request, bankAccount);

        URI location = buildFinancialGoalUri(bankAccount.getId());
        return ResponseEntity.created(location).body(financialGoalService.createGoal(bankAccount));
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

    private URI buildFinancialGoalUri (String financialGoalId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(financialGoalId)
                .toUri();
    }
}
