package com.banking.smartbankingassistant.service;

import com.banking.smartbankingassistant.dto.*;

public interface AIService {

    ClassifyResponse classify(EnquiryRequest request);

    SummaryResponse summarize(EnquiryRequest request);

    AskResponse ask(AskRequest request);
}
