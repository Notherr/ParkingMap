package com.loopy.web;

import com.loopy.domain.parkingrecord.ParkingRecord;


import com.loopy.service.ParkingRecordService;
import com.loopy.web.dto.EndTimeUpdateRequestDto;
import com.loopy.web.dto.ParkingRecordSaveRequestDto;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @PutMapping("api_v1/parking_record_end/{p_id}")
    public ResponseEntity<?> updateParkingRecord(@PathVariable Long p_id, @RequestBody EndTimeUpdateRequestDto requestDto) {
        parkingRecordService.update(p_id, requestDto);

        return ResponseEntity.ok().body(requestDto);
    }
}
