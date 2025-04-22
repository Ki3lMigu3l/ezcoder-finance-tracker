package br.dev.ezcoder.ezfinancepro.repositories;

import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {}
