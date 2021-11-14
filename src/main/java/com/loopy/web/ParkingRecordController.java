package com.loopy.web;

import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.service.ParkingRecordService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<?> saveParkingRecord(@RequestBody ParkingRecordDTO parkingRecordDTO) {
        Long accountId = parkingRecordDTO.getAccountId();
        LocalDateTime startTime = LocalDateTime.now();
        parkingRecordService.saveInitParkingRecord(accountId, startTime);

        return ResponseEntity.ok().body("parking record save");
    }

    @Data
    static class ParkingRecordDTO {

        private Long accountId;
    }
}
