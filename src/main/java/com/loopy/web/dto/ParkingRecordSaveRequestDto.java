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

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ParkingRecordSaveRequestDto {
    private Long accountId;
    private Long parkingLotId;
    private LocalDateTime startTime;

    private AccountRepository accountRepository;
    private ParkingLotRepository parkingLotRepository;

    @Builder
    public ParkingRecordSaveRequestDto(Long accountId, Long parkingLotId, LocalDateTime startTime) {
        this.accountId = accountId;
        this.parkingLotId = parkingLotId;
        this.startTime = startTime;
    }

    public ParkingRecord toParkingRecordEntity() {
        Account account = accountRepository.findById(accountId).get();
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).get();

        return ParkingRecord.builder()
                .account(account)
                .parkingLot(parkingLot)
                .startTime(startTime).build();
    }

    public RecordCache toRecordCacheEntity() {
        Account account = accountRepository.findById(accountId).get();
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).get();

        ParkingRecord parkingRecord = ParkingRecord.builder()
                .account(account)
                .parkingLot(parkingLot)
                .startTime(startTime).build();

        return RecordCache.builder()
                .parkingRecord(parkingRecord)
                .using(true).build();
    }




}
