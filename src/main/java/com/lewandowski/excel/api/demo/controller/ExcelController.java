package com.lewandowski.excel.api.demo.controller;

import com.lewandowski.excel.api.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @GetMapping("/list")
    public String showPresentList() throws IOException, GeneralSecurityException {

        List<List<Object>> result = excelService.getDataFormWorkbook();

        System.out.println(result);
        return "index.jsp";
    }

}
