package com.danal.chatting.chatting.service;

import com.danal.chatting.chatting.dto.ChatRoomDTO;
import com.danal.chatting.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;

    public ResponseEntity<?> findAllRooms() {
        List<ChatRoomDTO> allRooms = chatRoomRepository.findAllRooms();
        return new ResponseEntity<>(allRooms, HttpStatus.OK);
    }

    public ResponseEntity<?> createChatRoom(String name) {
        ChatRoomDTO chatRoomDTO = chatRoomRepository.createChatRoomDTO(name);
        return new ResponseEntity<>(chatRoomDTO, HttpStatus.OK);
    }

    public Object findRoomById(String roomId) {
        return null;
    }
}
