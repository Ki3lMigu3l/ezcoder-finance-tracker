package br.dev.ezcoder.ezfinancepro.exception;

import br.dev.ezcoder.ezfinancepro.model.entity.Role;
import br.dev.ezcoder.ezfinancepro.model.enums.RoleType;

import java.util.Set;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(Set<RoleType> missingRoles) {
        super("Roles not found: " + missingRoles);
    }
}
