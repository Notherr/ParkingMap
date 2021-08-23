package com.loopy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class RestApiController {

    @GetMapping("/apitest3")
    public String callapihttp(){

        StringBuffer result = new StringBuffer(); // String 클래스와 달리 내부에 버퍼를 가지고 있으며 수정 추가가 가능하다. String클래스는 read만 가능.
        try{    // 자바 예외 처리 문법 try-catch 문.
            String urlstr = "http://api.data.go.kr/openapi/tn_pubr_prkplce_info_api?" + // 요청 주소
                    "ServiceKey=dOWtJ6fk48FpZ%2FdZJMmDke4Ouo0FzOmLli6gNIlWSEPAY4O9m4FmRBlij%2FEa5F0A584FS44QqJOxxpjRYQneAQ%3D%3D" + // 서비스 키 (일반 인증키)
                    "&type=json" + //json으로 하면 json타입으로 출력.
                    "&pageNo=1" +   // 요청변수
                    "&numOfRows=1000" + // 요청변수
                    "&institutionNm=서울시설공단";    // 요청변수
            URL url = new URL(urlstr); // URL 클래스 : URL클래스를 이용하여 연결된 상대편으로부터 데이터를 읽어온다.
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection(); // URL 메소드 openConnection : URL 주소의 원격 객체에 접속한 뒤 통신가능한 openConnection객체 반환.
                                                                                        // HttpURLConnection 클래스 : 사용자 인증이나 보안이 설정되어 있지 않은 웹서버에 접속하여 파일 등을 다운로드하는데 많이 사용한다.

            urlconnection.setRequestMethod("GET"); // GET형식으로 웹서버에 요청.

            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8")); // 입력되는 스트림에 대하여 버퍼를 갖는 객체를 생성하여 효율적으로 문자를 읽어들임.
                                                                                                                                // 한글 입력받기위해 chr단위로 받는 getInputStream 사용, 버퍼에 저장했다가 한번에 읽어들임.
            String returnLine;
            while((returnLine = br.readLine()) != null) {   // .readline()은 개행문자를 기준으로 끊어읽음.(개행문자 : 새줄을 표시하는 문자.)
                result.append(returnLine);
            }
            urlconnection.disconnect();
        }catch(Exception e){
            e.printStackTrace();    // 예외 발생시 오류출력.
        }
        return result.toString();
    }

    @GetMapping("/apitest4")
    public String callapihttp1(){

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
