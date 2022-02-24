package com.danal.chatting.member.repository;

import com.danal.chatting.entity.member.Member;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository{
    Optional<Member> findMemberByEmail(String email);

}
