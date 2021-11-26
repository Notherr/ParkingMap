package com.loopy.web;


import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.service.ParkingRecordService;
import com.loopy.web.dto.UsingSignalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GatewayRestApiController {

    ParkingRecordService parkingRecordService;

    @GetMapping("/api_v1/checking/{p_id}")
    public Boolean findById (@PathVariable Long p_id) {
        return parkingRecordService.findById(p_id).getUsed();
    }


}
