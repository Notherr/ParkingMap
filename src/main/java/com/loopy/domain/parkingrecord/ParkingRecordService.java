package com.loopy.domain.parkingrecord;

import com.loopy.domain.account.Account;
import com.loopy.domain.account.AccountRepository;
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

    @Transactional
    public void saveInitParkingRecord(Long accountId, LocalDateTime startTime) {

        try {
            Account account = accountRepository.findById(accountId).get();
            ParkingRecord parkingRecord = ParkingRecord.builder()
                    .account(account)
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
