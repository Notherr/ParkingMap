package com.loopy.service;

import com.loopy.domain.parkingrecord.ParkingRecord;
import com.loopy.domain.parkingrecord.ParkingRecordRepository;
import com.loopy.domain.parkingrecord.recordCache.RecordCache;
import com.loopy.domain.parkingrecord.recordCache.RecordCacheRepository;
import com.loopy.web.dto.ParkingRecordSaveRequestDto;
import com.loopy.web.dto.UsingSignalResponseDto;
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
    private final RecordCacheRepository recordCacheRepository;

    @Transactional
    public void save(ParkingRecordSaveRequestDto requestDto) {
        try {
            parkingRecordRepository.save(requestDto.toParkingRecordEntity());
            recordCacheRepository.save(requestDto.toRecordCacheEntity());

            log.info("new parking record saved at " + requestDto.getStartTime());

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

    public UsingSignalResponseDto findById(Long p_id) {
        try {
            RecordCache recordCache = recordCacheRepository.findByParkinglotId(p_id);
            return new UsingSignalResponseDto(recordCache);
        } catch (NoSuchElementException exception) {
            log.warn("not exist parking record id");
            throw exception;
        }
    }
}