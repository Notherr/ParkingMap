package com.loopy.domain.parkinglot;

import com.loopy.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Column(nullable = false)
    private String pName;

    @Column(nullable = false)
    private String type;

    private Integer basicTime;

    private Integer basicCharge;

    @Column(nullable = false)
    private String openDay;

    @Embedded
    private Address address;
}
