package com.lewandowski.excel.api.demo.service;

import com.lewandowski.excel.api.demo.dto.ExcelDto;

import java.util.List;

public interface ExcelServiceDto {

    List<ExcelDto> getExcelDtoList(List<String> stringList);

}
