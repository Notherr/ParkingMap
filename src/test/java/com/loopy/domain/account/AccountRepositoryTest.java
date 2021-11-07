package com.loopy.domain.account;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @After
    public void cleanup() {
        accountRepository.deleteAll();
    }

    @Test
    public void 유저정보불러오기() {
        //given
        String name = "limseongkyu";
        String email = "96x60812@gmail.com";
        String picture = "https:/sdfsdf/sdf.jpg";
        accountRepository.save(Account.builder()
                .name(name)
                .email(email)
                .profileImage(picture)
                .build());

        //when
        List<Account> userList = accountRepository.findAll();

        //then
        Account user = userList.get(0);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getProfileImage()).isEqualTo(picture);
    }


}
