package com.loopy.domain.account;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/api_v1/account")
    public ResponseEntity<?> saveAccount(@RequestBody AccountDTO_v1 accountDTO_v1) {
        System.out.println(accountDTO_v1.getEmail() + " " + accountDTO_v1.getName());
        accountService.processNewAccount(accountDTO_v1.getName(), accountDTO_v1.getEmail());
        return ResponseEntity.ok().body("New Account created");
    }


    @Data
    static class AccountDTO_v1 {
        private String name;
        private String email;
    }


}
