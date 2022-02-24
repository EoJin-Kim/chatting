package com.danal.chatting.Role;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/role")
public class RoleController {

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
}
