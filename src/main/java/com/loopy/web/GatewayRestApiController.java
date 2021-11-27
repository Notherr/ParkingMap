package com.loopy.web;


import com.loopy.service.ParkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GatewayRestApiController {

    ParkingRecordService parkingRecordService;

    @GetMapping("/api_v1/checking/{p_id}")
    public Boolean findByPId (@PathVariable Long p_id) {
        return parkingRecordService.findByPId(p_id).isUsed();
    }


}
