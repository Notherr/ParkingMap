package com.loopy.domain.parkingrecord;

import com.loopy.domain.parkinglot.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

    ParkingRecord findByParkingLot(ParkingLot parkingLot);

}
