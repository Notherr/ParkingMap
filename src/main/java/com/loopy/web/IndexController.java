package com.loopy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/data")
    public String data() { return "data"; }

    @GetMapping("/test")
    public String test() {return "test";}

    @GetMapping("/list")
    public String list() {return "list";}

    @GetMapping("/info")
    public String info() {return "info";}

    @GetMapping("/using")
    public String using() {return "using";}
}
