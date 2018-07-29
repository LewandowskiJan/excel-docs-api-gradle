package com.lewandowski.excel.api.demo.service;

import com.lewandowski.excel.api.demo.dto.ExcelDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceDtoImpl implements ExcelServiceDto {

    @Override
    public List<ExcelDto> getExcelDtoList(List<String> stringList) {

        List<ExcelDto> excelDtoList = new ArrayList<>();

        for (String name : stringList){

            ExcelDto excelDto = new ExcelDto();
            excelDto.setName(name);

            excelDtoList.add(excelDto);

        }

        return excelDtoList;
    }
}
