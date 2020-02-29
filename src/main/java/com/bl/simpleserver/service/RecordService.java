package com.bl.simpleserver.service;

import com.bl.simpleserver.dto.RecordDto;
import com.bl.simpleserver.entity.Record;
import com.bl.simpleserver.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<RecordDto> getPageRecords() {
        return recordRepository.findAll(PageRequest.of(0, 10, Sort.by("id")))
                .get()
                .map(r -> new RecordDto(r.getId(), r.getDateTime(), r.getValue()))
                .collect(Collectors.toList());
    }

    public void createRecord(RecordDto recordDto) {
        log.info("create new record with value {}", recordDto.getValue());
        Record record = new Record(null, LocalDateTime.now(), recordDto.getValue());
        record = recordRepository.save(record);
        log.info("created new record with id {}", record.getId());
    }
}
