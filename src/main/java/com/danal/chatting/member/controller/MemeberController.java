package com.danal.chatting.member.controller;

import com.danal.chatting.jwt.JwtFilter;
import com.danal.chatting.jwt.TokenProvider;
import com.danal.chatting.jwt.dto.TokenDto;
import com.danal.chatting.member.dto.LoginDto;
import com.danal.chatting.member.dto.MemberInfoDto;
import com.danal.chatting.member.service.MemberService;
import com.danal.chatting.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemeberController {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // authenticate 함수가 실행 될때 CustomUserDetailsService 의 loadUserByUsername 실행된다
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('USER','MANAGER','ADMIN')")
    public ResponseEntity<?> memberInfo() {
        String email = SecurityUtil.getCurrentEmail().orElseThrow();
        MemberInfoDto memberInfoDto = memberService.findMemberInfo(email);
        return new ResponseEntity<>(memberInfoDto, HttpStatus.OK);
    }



    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> memberList() {
        List<MemberInfoDto> memberInfoDtoList = memberService.findMemberList();
        return new ResponseEntity<>(memberInfoDtoList, HttpStatus.OK);
    }
}
