package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.entity.Role;
import br.dev.ezcoder.ezfinancepro.model.enums.RoleType;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleType roleType);
    boolean existsByName(RoleType roleType);
    void save(Role role);
}
