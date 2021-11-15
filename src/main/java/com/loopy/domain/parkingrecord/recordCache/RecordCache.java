package com.loopy.domain.parkingrecord.recordCache;

import com.loopy.domain.account.Account;
import com.loopy.domain.parkingrecord.ParkingRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@RedisHash(value = "recordCache")
public class RecordCache implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "r_id", referencedColumnName = "id")
    private ParkingRecord parkingRecord;
    **/

    @Column(nullable = false)
    private boolean using;

    @Column(nullable = false)
    private Long parkinglotId;

    @Column(nullable = false)
    private Long accountId;

}