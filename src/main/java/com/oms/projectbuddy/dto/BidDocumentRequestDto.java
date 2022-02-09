package com.oms.projectbuddy.dto;

import lombok.Data;

@Data
public class BidDocumentRequestDto {
    private Long id;
    private String documentStatus;
    private Double score;
}
