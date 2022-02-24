package com.danal.chatting.chatting.controller;

import com.danal.chatting.chatting.dto.ChatRoomNameDTO;
import com.danal.chatting.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Slf4j
public class RoomController {


    private final ChatService chatService;
    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ResponseEntity<?> rooms(){

        log.info("# All Chat Rooms");

        return chatService.findAllRooms();


    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestBody ChatRoomNameDTO chatRoomNameDTO){

        log.info("# Create Chat Room , name: " + chatRoomNameDTO.getRoomName());
        // 모든 속성을 세션에 저장하기 때문에 URL 노출이 없다.
        chatService.createChatRoom(chatRoomNameDTO.getRoomName());
        return "ok";
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(@RequestParam String roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", chatService.findRoomById(roomId));
    }
}