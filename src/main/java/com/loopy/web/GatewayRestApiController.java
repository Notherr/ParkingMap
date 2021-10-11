package com.loopy.web;


import com.loopy.web.dto.WantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GatewayRestApiController {

    @PostMapping("/want")
    public String postWant(@RequestBody WantRequestDto requestDto) {
        System.out.println(requestDto);
        if (requestDto.getValue().equals("1")){
            return "ok";
        }
        else{
            return "no";
        }
    }
}
