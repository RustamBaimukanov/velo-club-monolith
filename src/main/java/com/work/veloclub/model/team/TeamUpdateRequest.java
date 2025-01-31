package com.work.veloclub.model.team;

import jakarta.validation.constraints.Size;

public record TeamUpdateRequest(@Size(max = 255, message = "Длина названия слишком велика.") String name,
                                String photo) {
}
