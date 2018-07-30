package com.lewandowski.excel.api.demo.controller;

import com.lewandowski.excel.api.demo.dto.ExcelDto;
import com.lewandowski.excel.api.demo.service.ExcelServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    ExcelServiceDto excelServiceDto;

    @GetMapping("/show")
    public void showPresentList() throws IOException, GeneralSecurityException {

        List<List<ExcelDto>> result = excelServiceDto.getExcelDtoList();

        System.out.println(result);

    }

}
