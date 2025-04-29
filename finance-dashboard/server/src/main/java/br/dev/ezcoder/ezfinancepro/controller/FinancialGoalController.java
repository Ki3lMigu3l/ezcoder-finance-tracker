package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.dto.request.FinancialGoalRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.FinancialGoalResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.service.FinancialGoalService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/goal")
@RequiredArgsConstructor
public class FinancialGoalController {

    private final FinancialGoalService financialGoalService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<FinancialGoalResponse> createFinancialGoal(@RequestBody @Valid FinancialGoalRequest request) {
        UserModel userFound = userService.findUserById(request.userId());

        FinancialGoal financialGoal = financialGoalService.createGoal(request);
        URI location = buildFinancialGoalUri(financialGoal.getId());

        return ResponseEntity
                .created(location)
                .body(FinancialGoalResponse.entityToResponse(financialGoal));
    }

    @GetMapping
    public ResponseEntity<List<FinancialGoalResponse>> getAllGoals () {
        return ResponseEntity.ok(financialGoalService.findAllFinancialGoal());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialGoalResponse> getFinancialGoal (@PathVariable String id) {
        return ResponseEntity.ok(FinancialGoalResponse.entityToResponse(financialGoalService.findFinancialGoal(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialGoalResponse> updateTransaction (@PathVariable String id,
                                                                    @RequestBody @Valid FinancialGoalRequest request) {
        return ResponseEntity
                .ok(FinancialGoalResponse
                        .entityToResponse(financialGoalService.updateFinancialGoal(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFinancialGoal (@PathVariable String id) {
        financialGoalService.deleteFinancialGoal(id);
    }

    private URI buildFinancialGoalUri (String financialGoalId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(financialGoalId)
                .toUri();
    }
}
