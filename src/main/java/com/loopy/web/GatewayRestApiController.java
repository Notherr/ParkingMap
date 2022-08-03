package com.loopy.web;


import com.loopy.service.ParkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api_v1")
public class GatewayRestApiController {

    ParkingRecordService parkingRecordService;

    @GetMapping("/checking/{parkinglot_id}")
    public Boolean checkUsed (@PathVariable Long parkinglot_id) {
        /*
        해당 주차장이 사용중이라면 True, 사용중이 아니라면 False를 반환합니다.
         */
        return parkingRecordService.getUsed(parkinglot_id).isUsed();
    }


}
