package com.loopy.web.dto;

import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UsingSignalResponseDto {

    private final Long parkingLotId;
    private final boolean used;
    private final LocalDateTime startTime;
    private final Long accountId;
    private final Long parkingRecordId;

    public UsingSignalResponseDto(RecordCache recordCache) {
        this.parkingLotId = recordCache.getId();
        this.used = recordCache.isUsed();
        this.startTime = recordCache.getStartTime();
        this.accountId = recordCache.getAccountId();
        this.parkingRecordId = recordCache.getParkingRecordId();
    }
}
