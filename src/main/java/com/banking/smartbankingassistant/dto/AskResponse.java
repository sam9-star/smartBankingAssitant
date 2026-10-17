package com.banking.smartbankingassistant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AskResponse(
        @NotBlank
        @Size(max = 2000)
        String answer,

        @Size(max = 500)
        String disclaimer
) {}
