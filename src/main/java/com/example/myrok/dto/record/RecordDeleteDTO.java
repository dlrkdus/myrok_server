package com.example.myrok.dto.record;

import jakarta.validation.constraints.NotNull;

public record RecordDeleteDTO(
        @NotNull
        Long recordWriterId
) {
}
