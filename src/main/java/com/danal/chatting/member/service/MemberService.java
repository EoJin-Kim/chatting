package com.danal.chatting.member.service;

import com.danal.chatting.Role.dto.AlterRoleDto;
import com.danal.chatting.entity.Authority;
import com.danal.chatting.entity.member.Member;
import com.danal.chatting.member.dto.MemberInfoDto;
import com.danal.chatting.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    final private MemberRepository memberRepository;

    public MemberInfoDto findMemberInfo(String email) {
        try {
            Member findMember = memberRepository.findMemberByEmailDsl(email).orElseThrow(() -> new Exception("사용자가 없습니다."));
//            Member findMember = memberRepository.findMemberByEmail(email).orElseThrow(() -> new Exception("사용자가 없습니다."));
            MemberInfoDto memberInfoDto = new MemberInfoDto(findMember.getEmail(),findMember.getName(),findMember.getAuthority().getRole().toString());
            return memberInfoDto;

        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }


    }

    public List<MemberInfoDto> findMemberList() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberInfoDto> memberInfoDtoList = memberList.stream()
                .map(member -> {
                    return new MemberInfoDto(member.getEmail(), member.getName(), member.getAuthority().getRole().toString());
                })
                .collect(Collectors.toList());
        return memberInfoDtoList;
    }

    @Transactional
    public MemberInfoDto alterMemberRole(AlterRoleDto alterRoleDto) {
        try {
            Member alterMember = memberRepository.findMemberByEmail(alterRoleDto.getEmail()).orElseThrow(() -> new Exception("해당 계정 없음"));

            Authority authority = memberRepository.findAuthorityByRole(alterRoleDto.getRole());
            alterMember.setAuthority(authority);
            MemberInfoDto memberInfoDto = new MemberInfoDto(alterMember.getEmail(), alterMember.getName(), alterMember.getAuthority().getRole().toString());
            return memberInfoDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }

    }
}
