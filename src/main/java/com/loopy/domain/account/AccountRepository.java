package com.loopy.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNicknameAndEmail(String nickname, String email);
}
