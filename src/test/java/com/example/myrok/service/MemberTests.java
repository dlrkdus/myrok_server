package com.example.myrok.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MemberTests {
    @Autowired
    MemberService memberService;
//    @Test
//    public void testRegister(){
//        memberService.checkMemberHaveProject(1L);
//    }
}
