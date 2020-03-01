package com.bl.simpleserver.resource;

import com.bl.simpleserver.dto.RecordDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/records")
public interface RecordApiResource {

    @GetMapping()
    List<RecordDto> getRecords();

    @PostMapping()
    void createRecord(@RequestBody RecordDto record);
}
