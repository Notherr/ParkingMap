package com.loopy.service;

import com.loopy.domain.account.Account;
import com.loopy.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    public void processNewAccount(String name, String email) {

        if (accountRepository.findByNameAndEmail(name, email) == null) {
            Account new_account = Account.builder()
                    .email(email)
                    .nickname(name)
                    .build();
            accountRepository.save(new_account);

        } else {
            log.warn("given name and email are already exist");
        }

}
}
