package com.loopy.service;

import com.loopy.domain.account.Account;
import com.loopy.domain.account.AccountRepository;
import com.loopy.domain.parkinglot.ParkingLot;
import com.loopy.domain.parkinglot.ParkingLotRepository;
import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.domain.parkingrecord.ParkingRecordRepository;
import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import com.loopy.domain.parkingrecord.recordCache.RecordCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingRecordService {

    private final ParkingRecordRepository parkingRecordRepository;
    private final AccountRepository accountRepository;
    private final RecordCacheRepository recordCacheRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Transactional
    public void saveInitParkingRecord(Long accountId, Long parkingLotId, LocalDateTime startTime) {
        try {
            Account account = accountRepository.findById(accountId).get();
            ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).get();
            ParkingRecord parkingRecord = ParkingRecord.builder()
                    .account(account)
                    .parkingLot(parkingLot)
                    .startTime(startTime).build();
            parkingRecordRepository.save(parkingRecord);
            RecordCache recordCache = new RecordCache();
            recordCache.setId(parkingRecord.getId());

            recordCacheRepository.save(recordCache);

            log.info("new parking record saved :: " + parkingRecord.getId());

        } catch (NoSuchElementException exception) {
            log.warn("not exist account id");
            return;
        }
    }

    public ParkingRecord getParkingRecordById(Long id) {
        try {
            RecordCache recordCache = recordCacheRepository.findById(id).get();
            return parkingRecordRepository.findById(recordCache.getId()).get();
        } catch (NoSuchElementException exception) {
            log.warn("not exist parking record id");
            throw exception;
        }
    }
}