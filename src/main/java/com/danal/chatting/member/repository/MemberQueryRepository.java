package com.danal.chatting.member.repository;

import com.danal.chatting.Role.Role;
import com.danal.chatting.entity.Authority;
import com.danal.chatting.entity.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MemberQueryRepository {
    public Optional<Member> findMemberByEmailDsl(String email);

    public Authority findAuthorityByRole(Role role);
}
