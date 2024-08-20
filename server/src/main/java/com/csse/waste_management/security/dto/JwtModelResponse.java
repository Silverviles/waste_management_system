package com.csse.waste_management.security.dto;

import java.io.Serial;
import java.io.Serializable;

public record JwtModelResponse(String token, String message) implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
}
