package com.loopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//메인 클래스.
// 스프링부트 자동 설정, BEAN 읽기 및 생성 자동화.
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        //WAS 실행
        SpringApplication.run(Application.class, args);
    }
}
