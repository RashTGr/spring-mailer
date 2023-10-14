package com.rashadla.mailinginspring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestDetails {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String attachment;

}
