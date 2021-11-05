package com.loopy.domain.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}
