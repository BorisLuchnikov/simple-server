package com.bl.simpleserver.resource;

import com.bl.simpleserver.dto.ChartDataDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/charts")
public interface ChartApiResource {

    @GetMapping("/data")
    ChartDataDto getChartData();
}
