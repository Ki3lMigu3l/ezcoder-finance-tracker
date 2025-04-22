package br.dev.ezcoder.ezfinancepro.repositories;

import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinancialGoalRepository extends MongoRepository<FinancialGoal, String> {
}
