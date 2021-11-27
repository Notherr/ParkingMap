package com.loopy.service;

import com.loopy.domain.account.Account;
import com.loopy.domain.account.AccountRepository;
import com.loopy.domain.parkinglot.ParkingLot;
import com.loopy.domain.parkinglot.ParkingLotRepository;
import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.domain.parkingrecord.ParkingRecordRepository;
import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import com.loopy.domain.parkingrecord.recordCache.RecordCacheRepository;
import com.loopy.web.dto.ParkingRecordUpdateRequestDto;
import com.loopy.web.dto.ParkingRecordSaveRequestDto;
import com.loopy.web.dto.UsingSignalResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.NoSuchElementException;


@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingRecordService {

    private final ParkingRecordRepository parkingRecordRepository;
    private final RecordCacheRepository recordCacheRepository;
    private final AccountRepository accountRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Transactional
    public void save(ParkingRecordSaveRequestDto requestDto) {
        try {
            Account account = accountRepository.findById(requestDto.getAccountId()).get();
            ParkingLot parkingLot = parkingLotRepository.findById(requestDto.getParkingLotId()).get();

            ParkingRecord parkingRecord = ParkingRecord.builder()
                            .account(account)
                            .parkingLot(parkingLot)
                            .startTime(requestDto.getStartTime()).build();

            parkingRecordRepository.save(parkingRecord);

            RecordCache recordCache = RecordCache.builder()
                    .id(parkingLot.getId())
                    .accountId(parkingRecord.getAccount().getId())
                    .parkingRecordId(parkingRecord.getId())
                    .used(true)
                    .startTime(parkingRecord.getStartTime())
                    .build();


            System.out.println(recordCache);
            recordCacheRepository.save(recordCache);

//            log.info("new parking record saved at " + requestDto.getStartTime());
//            log.info("all records in cache :: ");
//            recordCacheRepository.findAll().forEach(System.out::println);
//            log.info(" ");

        } catch (NoSuchElementException exception) {
            log.warn("not exist account id");
            return;
        }
    }

    @Transactional
    public void update(ParkingRecordUpdateRequestDto requestDto) {
        try {

            RecordCache cache = recordCacheRepository.findById(requestDto.getParkingLotId()).get();
            if (!cache.isUsed()) {
                throw new RuntimeException();
            }
            ParkingLot parkingLot = parkingLotRepository.getOne(cache.getId());
            ParkingRecord parkingRecord = parkingRecordRepository.findById(cache.getParkingRecordId()).get();

            Duration duration = Duration.between(cache.getStartTime(), requestDto.getEndTime());
            int price = (int) (((duration.getSeconds()/60) / parkingLot.getBasicTime() ) * parkingLot.getBasicCharge());

            parkingRecord.update(requestDto.getEndTime(), price);
            recordCacheRepository.delete(cache);

            log.info("service is ended at " + requestDto.getEndTime());

        } catch (NoSuchElementException exception){
            log.warn("not exist parkinglot id");
            return;
        }
    }



    public ParkingRecord getParkingRecordById(Long id) {
        try {
            ParkingRecord parkingRecord = parkingRecordRepository.findById(id).get();
            return parkingRecord;

        } catch (NoSuchElementException exception) {
            log.warn("not exist parking record id");
            throw exception;
        }
    }

    public UsingSignalResponseDto findById(Long p_id) {
        try {
            RecordCache recordCache = recordCacheRepository.findById(p_id).get();
            return new UsingSignalResponseDto(recordCache);
        } catch (NoSuchElementException exception) {
            log.warn("not exist parking lot id");
            throw exception;
        }
    }
}