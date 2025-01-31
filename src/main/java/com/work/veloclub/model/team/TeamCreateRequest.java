package com.work.veloclub.model.team;

import jakarta.validation.constraints.Size;

public record TeamCreateRequest(@Size(max = 255, message = "Длина названия слишком велика.")
                                String name, String photo) {
}
