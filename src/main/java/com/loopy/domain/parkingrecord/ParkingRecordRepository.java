package com.loopy.domain.parkingrecord;

import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

    ParkingRecord findByParkinglotId (Long parkinglotId);

}
