package com.loopy.domain.accounts;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //???
    @Builder
    public Accounts(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;

    }

}
