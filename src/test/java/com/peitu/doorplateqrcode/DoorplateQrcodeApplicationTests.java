package com.peitu.doorplateqrcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoorplateQrcodeApplicationTests {

    @Test
    public void contextLoads() {

        String date = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss").format(new Date());
        System.out.println(date);

    }

}
