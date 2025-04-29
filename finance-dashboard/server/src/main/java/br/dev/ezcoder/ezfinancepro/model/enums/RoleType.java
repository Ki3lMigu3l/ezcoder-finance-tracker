package br.dev.ezcoder.ezfinancepro.model.enums;

public enum RoleType {
    ROLE_USER ("Usuario comum - acesso limitado"),
    ROLE_ADMIN ("Administrador - acesso total"),
    ROLE_MODERATOR ("Moderador - acesso parcial");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
