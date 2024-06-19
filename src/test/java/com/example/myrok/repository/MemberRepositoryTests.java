package com.example.myrok.repository;

import com.example.myrok.domain.Member;
import com.example.myrok.type.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void testInsertMember(){


        for(int i = 0; i<10; i++){
            Member member = Member.builder()
                    .socialId("user" + i + "@aaa.com")
                    .password(passwordEncoder.encode("111"))
                    .name("user" + i)
                    .build();

            if(i<=5){
                member.addRole(MemberRole.USER);
            }

            memberRepository.save(member);
        }//end
    }


    //권한 읽어오기
//    @Test
//    public void testRead() {
//        for(int i = 0; i<10; i++){
//            Member member = memberRepository.findBySocialId("user" + i + "@aaa.com");
//            log.info(member);
//        }
//    }
}
