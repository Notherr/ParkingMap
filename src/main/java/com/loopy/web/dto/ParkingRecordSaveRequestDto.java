package com.loopy.web.dto;

import com.loopy.domain.account.Account;
import com.loopy.domain.account.AccountRepository;
import com.loopy.domain.parkinglot.ParkingLot;
import com.loopy.domain.parkinglot.ParkingLotRepository;
import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ParkingRecordSaveRequestDto {
    private Long accountId;
    private Long parkingLotId;
    private final LocalDateTime startTime = LocalDateTime.now();

    @Builder
    public ParkingRecordSaveRequestDto(Long accountId, Long parkingLotId) {
        this.accountId = accountId;
        this.parkingLotId = parkingLotId;
    }

    /**
    private  AccountRepository accountRepository;
    private ParkingLotRepository parkingLotRepository;

    public ParkingRecord toParkingRecordEntity() {
        Account account = accountRepository.findById(accountId).get();
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).get();

        return ParkingRecord.builder()
                .account(account)
                .parkingLot(parkingLot)
                .startTime(startTime).build();
    }

    public RecordCache toRecordCacheEntity() {

        return RecordCache.builder()
                .parkinglotId(parkingLotId)
                .accountId(accountId)
                .using(true).build();
    }
     **/
}
