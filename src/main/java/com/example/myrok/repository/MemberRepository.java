package com.example.myrok.repository;

import com.example.myrok.domain.Member;
import com.example.myrok.type.LoginProvider;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m.name FROM Member m WHERE m.id =:id")
    String findNameById(@Param("id")Long id);
    Optional<Member> findBySocialId(String socialId);

    Optional<Member> findBySocialIdAndLoginProvider(String socialId, LoginProvider loginProvider);
}
