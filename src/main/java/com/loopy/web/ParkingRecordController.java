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
@RequestMapping("/api-v1")
public class ParkingRecordController {

    private final ParkingRecordService parkingRecordService;

    @GetMapping("/records")
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

    @PostMapping("/records")
    public ResponseEntity<?> startParkingRecord(@RequestBody ParkingRecordSaveRequestDto requestDto) {
        parkingRecordService.start(requestDto);

        return ResponseEntity.ok().body(requestDto);
    }

    @PatchMapping("/records")
    public ResponseEntity<?> endParkingRecord(@RequestBody ParkingRecordUpdateRequestDto requestDto) {
        parkingRecordService.end(requestDto);

        return ResponseEntity.ok().body(requestDto);
    }

    @GetMapping("/record-cache")
    public ResponseEntity<?> getRecordCache(@RequestParam Long id) {
        /*
        recordCache id로 기록된 주차장의 id와 used 컬럼을 반환합니다.
         */
        try {
            RecordCache cache = parkingRecordService.getRecordCacheById(id);
            return ResponseEntity.ok().body(
                    "parkingLotId : " + cache.getId() + ", Used :  " + cache.isUsed()
            );
        } catch (NoSuchElementException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> getUsed(@RequestParam Long id) {
        /*
        recordCache id로 used 컬럼 값을 반환합니다.
         */
        try {
            RecordCache cache = parkingRecordService.getRecordCacheById(id);
            return ResponseEntity.ok(cache.isUsed());
        } catch (NoSuchElementException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
