package com.danal.chatting.Role;


import com.danal.chatting.Role.dto.AlterRoleDto;
import com.danal.chatting.member.dto.MemberInfoDto;
import com.danal.chatting.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final MemberService memberService;

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String> userRoleCheck(HttpServletRequest request) {
        return ResponseEntity.ok("user");
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<String> managerRoleCheck(HttpServletRequest request) {
        return ResponseEntity.ok("manager");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> adminRoleCheck(HttpServletRequest request) {
        return ResponseEntity.ok("admin");
    }

    @PostMapping("/alter")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> alterRole(@RequestBody AlterRoleDto alterRoleDto) {
        MemberInfoDto memberInfoDto = memberService.alterMemberRole(alterRoleDto);
        return ResponseEntity.ok("admin");
    }
}
