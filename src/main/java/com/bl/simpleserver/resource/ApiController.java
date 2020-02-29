package com.bl.simpleserver.resource;

import com.bl.simpleserver.dto.RecordDto;
import com.bl.simpleserver.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController implements ApiResource {

    private final RecordService recordService;

    @Override
    public List<RecordDto> getRecords() {
        log.info("start getting records");
        return recordService.getPageRecords();
    }

    @Override
    public void createRecord(RecordDto record) {
        log.info("start creating record");
        recordService.createRecord(record);
        log.info("finish creating record");
    }
}
