package com.danal.chatting.member.repository;

import com.danal.chatting.entity.member.Member;
import com.danal.chatting.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
}
