//package com.loopy.domain.parkingrecord;
//
//import com.loopy.domain.account.Account;
//import com.loopy.domain.account.AccountRepository;
//import com.loopy.service.ParkingRecordService;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@SpringBootTest
//class ParkingRecordServiceTest {
//
//    @Autowired
//    private ParkingRecordService parkingRecordService;
//
//    @Autowired
//    private ParkingRecordRepository parkingRecordRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @BeforeEach
//    @Transactional
//    public void beforeEach() {
//        Account account = Account.builder()
//                .name("hyungjun")
//                .email("hyungjun@gmail.com")
//                .build();
//        accountRepository.save(account);
//    }
//
//    @AfterEach
//    public void afterEach() {
//        parkingRecordRepository.deleteAll();
//
//    }
//
//    @DisplayName("주차 기록 저장 테스트")
//    @Test
//    public void save_parking_record_test() {
//
//
//        LocalDateTime startTime = LocalDateTime.now();
//        parkingRecordService.saveInitParkingRecord(1L, startTime);
//
//        ParkingRecord parkingRecord = parkingRecordRepository.findById(1L).get();
//
//        Assertions.assertNotNull(parkingRecord);
//
//    }
//}