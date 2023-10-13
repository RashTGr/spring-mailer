package com.rashadla.mailinginspring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String body;

}
