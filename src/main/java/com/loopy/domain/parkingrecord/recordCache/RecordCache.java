package com.loopy.domain.parkingrecord.recordCache;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@RedisHash(value = "recordCache")
public class RecordCache implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


}
