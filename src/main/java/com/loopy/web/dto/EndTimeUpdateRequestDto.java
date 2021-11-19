package com.loopy.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EndTimeUpdateRequestDto {

    private LocalDateTime endTime = LocalDateTime.now();

}
