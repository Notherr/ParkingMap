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


    public void processNewAccount(String nickname, String email) {

        if (accountRepository.findByNicknameAndEmail(nickname, email) == null) {
            Account new_account = Account.builder()
                    .email(email)
                    .nickname(nickname)
                    .build();
            accountRepository.save(new_account);

        } else {
            log.warn("given name and email are already exist");
        }

}
}
