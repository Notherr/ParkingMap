package com.loopy.domain.accounts;


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
public class AccountsRepositoryTest {

    @Autowired
    AccountsRepository accountsRepository;

    @After
    public void cleanup() {
        accountsRepository.deleteAll();
    }

    @Test
    public void 유저정보불러오기() {
        //given
        String name = "limseongkyu";
        String email = "96x60812@gmail.com";
        String picture = "https:/sdfsdf/sdf.jpg";

        accountsRepository.save(Accounts.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .build());

        //when
        List<Accounts> userList = accountsRepository.findAll();

        //then
        Accounts user = userList.get(0);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPicture()).isEqualTo(picture);
    }


}
