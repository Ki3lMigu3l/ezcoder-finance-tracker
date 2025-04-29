package br.dev.ezcoder.ezfinancepro.util;

import br.dev.ezcoder.ezfinancepro.model.entity.Role;
import br.dev.ezcoder.ezfinancepro.model.enums.RoleType;
import br.dev.ezcoder.ezfinancepro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void initRoles() {
        List<RoleType> defaultRoles = List.of(
                RoleType.ROLE_USER,
                RoleType.ROLE_ADMIN,
                RoleType.ROLE_MODERATOR
        );

        defaultRoles.forEach(roleType -> {
            if (!roleService.existsByName(roleType)) {
                Role role = new Role();
                role.setName(roleType);
                role.setPermissions(Set.of("DEFAULT_PERMISSION"));
                roleService.save(role);
                System.out.println("Role criada: " + roleType);
            }
        });
    }
}
