package com.bl.simpleserver.resource;

import com.bl.simpleserver.dto.ChartDataDto;
import com.bl.simpleserver.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChartApiController implements ChartApiResource {

    private final RecordService recordService;

    @Override
    public ChartDataDto getChartData() {
        log.info("start getting chart data");
        return recordService.getChartDataDto();
    }
}
