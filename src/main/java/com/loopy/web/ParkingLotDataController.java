package com.loopy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api_v1")
public class ParkingLotDataController {

    @GetMapping("/parkinglot-data")
    public String getData(){

        StringBuffer result = new StringBuffer(); // StringBuffer 클래스의 객체(인스턴스) 생성
        try{ // 자바 예외 처리 문법 try-catch 문.
            String urlstr = "http://api.data.go.kr/openapi/tn_pubr_prkplce_info_api?" + // 요청 주소
                    "ServiceKey=dOWtJ6fk48FpZ%2FdZJMmDke4Ouo0FzOmLli6gNIlWSEPAY4O9m4FmRBlij%2FEa5F0A584FS44QqJOxxpjRYQneAQ%3D%3D" + // 서비스 키 (일반 인증키)
                    "&type=json" + //json으로 하면 json타입으로 출력.
                    "&pageNo=1" + // 요청변수
                    "&numOfRows=1000" + // 요청변수
                    "&institutionNm=서울시설공단"; // 요청변수
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlconnection.disconnect();
        }catch(Exception e){
            e.printStackTrace(); // 예외 발생시 오류출력.
        }
        return result.toString();
    }
}
