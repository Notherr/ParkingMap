package com.loopy.web;


import com.loopy.web.dto.WantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GatewayRestApiController {

    String val = "0";
    //@RequestMapping(value="/want", method={RequestMethod.GET, RequestMethod.POST})
    @RequestMapping("/want")
    public String postWant(WantRequestDto requestDto) {
        return requestDto.getValue();

    }

}
