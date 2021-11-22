package com.loopy.domain.parkingrecord.recordCache;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@RedisHash(value = "recordCache")
public class RecordCache implements Serializable {

    @Id
    @Column(name = "parkingLotId")
    private Long id;

    @Column(nullable = false)
    private boolean using;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private Long accountId;

    @Column(name = "parkingRecordId")
    private Long parkingRecordId;
}
