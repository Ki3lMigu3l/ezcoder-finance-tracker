package br.dev.ezcoder.ezfinancepro.service.impl;

import br.dev.ezcoder.ezfinancepro.model.entity.Role;
import br.dev.ezcoder.ezfinancepro.model.enums.RoleType;
import br.dev.ezcoder.ezfinancepro.repository.RoleRepository;
import br.dev.ezcoder.ezfinancepro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleType roleType) {
        return roleRepository.findByName(roleType.name());
    }

    @Override
    public boolean existsByName(RoleType roleType) {
        return roleRepository.existsByName(roleType);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
