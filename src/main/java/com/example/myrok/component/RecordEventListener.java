package com.example.myrok.component;


import com.example.myrok.domain.Record;
import com.example.myrok.component.event.RecordSavedEvent;
import com.example.myrok.repository.RecordRepository;
import com.example.myrok.service.openapi.ChatCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@EnableAsync
@Component
public class RecordEventListener {

    private final ChatCompletionService chatCompletionService;
    private final RecordRepository recordRepository;

    @Autowired
    public RecordEventListener(ChatCompletionService chatCompletionService, RecordRepository recordRepository) {
        this.chatCompletionService = chatCompletionService;
        this.recordRepository = recordRepository;
    }

    @Async
    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 1600)
    public void handleRecordSavedEvent(RecordSavedEvent event) {
        Record record = event.getRecord();
        String recordContent = record.getRecordContent();
        String summary = chatCompletionService.chatCompletions(recordContent);
        if(!StringUtils.hasLength(summary)){
            throw new RuntimeException();
        }
        record.updateSummary(summary);
    }
}
