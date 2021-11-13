package com.loopy.domain.parkingrecord;

import com.loopy.domain.account.Account;
import com.loopy.domain.account.AccountRepository;
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

    @Transactional
    public void saveInitParkingRecord(Long accountId, LocalDateTime startTime) {

        try {
            Account account = accountRepository.findById(accountId).get();
            ParkingRecord parkingRecord = ParkingRecord.builder()
                    .account(account)
                    .startTime(startTime).build();
            parkingRecordRepository.save(parkingRecord);
        } catch (NoSuchElementException exception) {
            log.warn("not exist account id");
            return;
        }


    }

    public ParkingRecord getParkingRecordById(Long id) {
        return parkingRecordRepository.findById(id).get();
    }
}
