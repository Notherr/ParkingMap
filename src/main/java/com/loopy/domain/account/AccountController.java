package com.loopy.domain.account;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/api_v1/account")
    public ResponseEntity<?> saveAccount(AccountDTO_v1 accountDTO_v1) {
        accountService.processNewAccount(accountDTO_v1.getId(), accountDTO_v1.getEmail(), accountDTO_v1.getName());
        return ResponseEntity.ok().body("New Account created");
    }


    @Data
    private class AccountDTO_v1 {
        private Long id;
        private String name;
        private String email;
    }


}
