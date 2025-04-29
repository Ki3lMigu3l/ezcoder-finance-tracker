package br.dev.ezcoder.ezfinancepro.repository;

import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialGoalRepository extends MongoRepository<FinancialGoal, String> {}
