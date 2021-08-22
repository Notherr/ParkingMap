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

        StringBuffer result = new StringBuffer();
        try{
            String urlstr = "http://api.data.go.kr/openapi/tn_pubr_prkplce_info_api?" +
                    "ServiceKey=dOWtJ6fk48FpZ%2FdZJMmDke4Ouo0FzOmLli6gNIlWSEPAY4O9m4FmRBlij%2FEa5F0A584FS44QqJOxxpjRYQneAQ%3D%3D" +
                    "&type=xml" + //json으로 하면 json타입으로 출력.
                    "&pageNo=0" +
                    "&numOfRows=100";
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlconnection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result+ "</xmp>";
    }
}
