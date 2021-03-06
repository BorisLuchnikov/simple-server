package com.bl.simpleserver.service;

import com.bl.simpleserver.dto.ChartDataDto;
import com.bl.simpleserver.dto.DatasetDto;
import com.bl.simpleserver.dto.RecordDto;
import com.bl.simpleserver.entity.Record;
import com.bl.simpleserver.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public ChartDataDto getChartDataDto() {
        List<RecordDto> recordDtos = getPageRecords();
        ChartDataDto chartDataDto = new ChartDataDto();
        chartDataDto.setLabels(recordDtos.stream().map(RecordDto::getDateTime).collect(Collectors.toList()));
        DatasetDto datasetDto = new DatasetDto();
        datasetDto.setData(recordDtos.stream().map(r -> Float.parseFloat(r.getValue())).collect(Collectors.toList()));
        datasetDto.setLabel(recordDtos.get(0) != null ? recordDtos.get(0).getName() : "");
        datasetDto.setBorderColor("#3e95cd");
        chartDataDto.setDatasets(Collections.singletonList(datasetDto));
        return chartDataDto;
    }

    public List<RecordDto> getPageRecords() {
        return recordRepository.findAll(PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "dateTime")))
                .get()
                .map(r -> new RecordDto(r.getId(), r.getDateTime(), r.getName(), r.getValue()))
                .sorted(Comparator.comparingLong(RecordDto::getId))
                .collect(Collectors.toList());
    }

    public void createRecord(RecordDto recordDto) {
        log.info("create new record with value {}", recordDto.getValue());
        Record record = new Record(null, LocalDateTime.now(), recordDto.getName(), recordDto.getValue());
        record = recordRepository.save(record);
        log.info("created new record with id {}", record.getId());
    }
}
