package br.dev.ezcoder.ezfinancepro.repository;

import br.dev.ezcoder.ezfinancepro.model.entity.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends MongoRepository<BankAccount, String> {}
