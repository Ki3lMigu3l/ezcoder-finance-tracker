package br.dev.ezcoder.ezfinancepro.repository;

import br.dev.ezcoder.ezfinancepro.model.entity.Role;
import br.dev.ezcoder.ezfinancepro.model.enums.RoleType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);
    boolean existsByName(RoleType roleType);
}
