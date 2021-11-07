package com.loopy.domain.parkinglot;

import com.loopy.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    private String type;

    private Integer basic_time;

    private Integer basic_change;

    @Embedded
    private Address address;
}
