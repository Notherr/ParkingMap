package com.loopy.web.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class WantRequestDto {
    private String value;



    public String getValue() {
        return value;
    }
}
