package br.dev.ezcoder.ezfinancepro.model.dto.response;

import java.time.Instant;
import java.util.Set;

public record AuthResponse(
        String id,
        String email,
        String firstName,
        String lastName,
        Set<String> roles,
        Instant createdAt
) {
}
