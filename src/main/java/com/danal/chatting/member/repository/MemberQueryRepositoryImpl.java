package com.danal.chatting.member.repository;

import com.danal.chatting.Role.Role;
import com.danal.chatting.entity.Authority;
import com.danal.chatting.entity.QAuthority;
import com.danal.chatting.entity.member.Member;
import com.danal.chatting.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.danal.chatting.entity.QAuthority.authority;
import static com.danal.chatting.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository{
    final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findMemberByEmailDsl(String email) {

        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne());
    }

    @Override
    public Authority findAuthorityByRole(Role role) {
        return queryFactory
                .selectFrom(authority)
                .where(authority.role.eq(role))
                .fetchOne();
    }
}
