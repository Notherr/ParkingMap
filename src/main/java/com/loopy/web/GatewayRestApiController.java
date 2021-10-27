package com.loopy.web;


import com.loopy.web.dto.WantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GatewayRestApiController {

    //@RequestMapping(value="/want", method={RequestMethod.GET, RequestMethod.POST})
    @RequestMapping("/want")
    public String postWant(WantRequestDto requestDto) {
        if (requestDto.getValue().equals("1")){
            return "ok";
        }
        else{
            return "no";
        }
    }

}
