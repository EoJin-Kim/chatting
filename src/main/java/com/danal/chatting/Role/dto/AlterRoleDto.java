package com.danal.chatting.Role.dto;

import com.danal.chatting.Role.Role;
import lombok.Data;

@Data
public class AlterRoleDto {
    private String email;
    private Role role;
}
