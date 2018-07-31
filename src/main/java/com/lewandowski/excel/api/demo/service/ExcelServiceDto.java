package com.lewandowski.excel.api.demo.service;

import com.lewandowski.excel.api.demo.dto.ExcelDto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface ExcelServiceDto {

    List<ExcelDto> getExcelDtoList() throws IOException, GeneralSecurityException;

}
