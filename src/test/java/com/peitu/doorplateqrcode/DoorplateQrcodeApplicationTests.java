package com.peitu.doorplateqrcode;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class DoorplateQrcodeApplicationTests {


    @Test
    public void contextLoads() {
        System.out.println(test());
    }

    private String test() {
        String t = "";
        try {
            String.valueOf(null);
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return t;
    }

}
