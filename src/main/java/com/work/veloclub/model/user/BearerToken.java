package com.work.veloclub.model.user;

import lombok.Data;

public record BearerToken(     String accessToken,
        String tokenType
) {
}
