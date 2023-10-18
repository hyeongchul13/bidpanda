package com.panda.back.domain.chat.service;


import com.panda.back.domain.chat.dto.ReceiveMessage;
import com.panda.back.domain.chat.entity.component.Message;
import com.panda.back.domain.chat.repository.ChatRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRecordService {
    private final ChatRecordRepository chatRecordRepository;
    @Transactional
    public void recordMessage(ReceiveMessage message) {

        log.info("{} 저장 시도", message.getRecordId());
        chatRecordRepository.findById(new ObjectId(message.getRecordId()))
                .ifPresent(chatRecord -> {
                    chatRecord.recordMessage(new Message(message, chatRecord.getMessageQnt()));
                });
        log.info("{} 채팅방 저장 완료" ,message.getRecordId());
    }

}
