package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.Person.PersonDao;
import com.example.FrameUpServer.Model.event.Event;
import com.example.FrameUpServer.Model.event.EventDao;
import org.junit.jupiter.api.Test;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
public class TestEvent {

    @Test
    void  getty()
    {
        String d ="20/12/2022";
        int d1 = Character.getNumericValue(d.charAt(0));
        int d2 = Character.getNumericValue(d.charAt(1));
        char s1 = d.charAt(2);
        int d4 = Character.getNumericValue(d.charAt(3));
        int d5 = Character.getNumericValue(d.charAt(4));
        char s2 =d.charAt(5);
        int d6 = Character.getNumericValue(d.charAt(6));
        int d7 =Character.getNumericValue(d.charAt(7));
        int d8 = Character.getNumericValue(d.charAt(8));
        int d9 = Character.getNumericValue(d.charAt(9));

        System.out.println(d1);
        if(d1>=0 && d1<4) {
            if(d2>=0 && d2<=10){
                if(s1=='/'){
                    if(d4>=0 && d4<=1){
                        if(d5>=0 && d5<=2){
                            if(s2=='/' && d6==2 && d7==0 && d8==2 && d9>=2)
                            {

                            }

                        }

                    }
                }

            }

        }
        else{
            System.out.println("day fail");
        }
    }
}
