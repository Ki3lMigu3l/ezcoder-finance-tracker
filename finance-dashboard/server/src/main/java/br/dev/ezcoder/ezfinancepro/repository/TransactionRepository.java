package br.dev.ezcoder.ezfinancepro.repository;

import br.dev.ezcoder.ezfinancepro.model.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {}
