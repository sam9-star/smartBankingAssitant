package com.banking.smartbankingassistant.dto;

import jakarta.validation.constraints.Size;

public record SummaryResponse(

    @Size(max = 500)
    String summary,

    @Size(max = 500)
    String suggestedNextStep
) {}
