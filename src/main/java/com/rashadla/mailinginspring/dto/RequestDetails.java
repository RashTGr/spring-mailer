package com.rashadla.mailinginspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RequestDetails {
    private String from;
    private String to;
    private String subject;
    private String body;
//    private String attachment;

}
