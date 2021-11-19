package com.loopy.domain.parkingrecord;

import com.loopy.domain.account.Account;
import com.loopy.domain.parkinglot.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "parkingRecord")
public class ParkingRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "p_id", referencedColumnName = "id")
    private ParkingLot parkingLot;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer price;

    private Boolean isPayed;

    public void update(LocalDateTime endTime, int price) {
        this.endTime = endTime;
        this.price = price;
    }
}
