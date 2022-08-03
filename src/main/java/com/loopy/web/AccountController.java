package com.loopy.web;

import com.loopy.service.AccountService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api-v1")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<?> saveAccount(@RequestBody AccountDTO_v1 accountDTO_v1) {
        System.out.println(accountDTO_v1.getEmail() + " " + accountDTO_v1.getNickname());
        accountService.processNewAccount(accountDTO_v1.getNickname(), accountDTO_v1.getEmail());
        return ResponseEntity.ok().body("New Account created");
    }


    @Data
    static class AccountDTO_v1 {
        private String nickname;
        private String email;
    }


}
