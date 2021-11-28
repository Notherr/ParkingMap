package com.loopy.web.dto;

import com.loopy.domain.parkinglot.ParkingLot;
import com.loopy.domain.parkinglot.ParkingLotRepository;
import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.domain.parkingrecord.ParkingRecordRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ParkingRecordUpdateRequestDto {

    private Long parkingLotId;
    private final LocalDateTime endTime = LocalDateTime.now();

    @Builder
    public ParkingRecordUpdateRequestDto(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

}
