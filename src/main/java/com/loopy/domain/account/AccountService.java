package com.loopy.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void processNewAccount(Long id, String email, String name) {

        Account account = Account.builder()
                .id(id)
                .email(email)
                .name(name)
                .build();
        accountRepository.save(account);
    }
}
