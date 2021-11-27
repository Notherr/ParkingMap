package com.loopy.web;

import com.loopy.domain.parkingrecord.ParkingRecord;


import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import com.loopy.service.ParkingRecordService;
import com.loopy.web.dto.ParkingRecordSaveRequestDto;
import com.loopy.web.dto.ParkingRecordUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class ParkingRecordController {

    private final ParkingRecordService parkingRecordService;

    @GetMapping("/api_v1/parking_record")
    public ResponseEntity<?> getParkingRecord(@RequestParam Long id) {
        try {
            ParkingRecord record = parkingRecordService.getParkingRecordById(id);
            return ResponseEntity.ok().body(
                    record.getAccount() + " " + record.getStartTime()
            );
        } catch (NoSuchElementException exception) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api_v1/parking_record")
    public ResponseEntity<?> saveParkingRecord(@RequestBody ParkingRecordSaveRequestDto requestDto) {
        parkingRecordService.save(requestDto);

        return ResponseEntity.ok().body(requestDto);
    }

    @PatchMapping("api_v1/parking_record_end")
    public ResponseEntity<?> updateParkingRecord(@RequestBody ParkingRecordUpdateRequestDto requestDto) {
        parkingRecordService.update(requestDto);

        return ResponseEntity.ok().body(requestDto);
    }

    @GetMapping("/api_v1/record_cache")
    public ResponseEntity<?> getRecordCache(@RequestParam Long id) {
        try {
            RecordCache cache = parkingRecordService.getRecordCacheById(id);
            return ResponseEntity.ok().body(
                    "parkingLotId : " + cache.getParkingRecordId() + ", Used :  " + cache.isUsed()
            );
        } catch (NoSuchElementException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
