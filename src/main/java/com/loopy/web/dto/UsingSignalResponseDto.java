package com.loopy.web.dto;

import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import lombok.Getter;

@Getter
public class UsingSignalResponseDto {

    private Boolean used;
    private Long parkinglotId;
    private Long accountId;

    public UsingSignalResponseDto(RecordCache recordCache) {
        this.used = recordCache.isUsed();
        this.parkinglotId = recordCache.getId();
        this.accountId = recordCache.getAccountId();
    }
}
