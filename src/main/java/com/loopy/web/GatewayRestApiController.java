package com.loopy.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayRestApiController {

    private static long startup = System.currentTimeMillis();
    private static long cnt = 0;

    @GetMapping("/counter")
    public

}
