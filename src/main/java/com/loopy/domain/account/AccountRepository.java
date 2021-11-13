package com.loopy.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNameAndEmail(String name, String email);
}
