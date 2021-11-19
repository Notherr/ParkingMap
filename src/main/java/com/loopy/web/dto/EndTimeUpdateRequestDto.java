package com.loopy.web.dto;

import com.loopy.domain.parkinglot.ParkingLot;
import com.loopy.domain.parkinglot.ParkingLotRepository;
import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.domain.parkingrecord.ParkingRecordRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EndTimeUpdateRequestDto {

    private LocalDateTime endTime = LocalDateTime.now();

}
