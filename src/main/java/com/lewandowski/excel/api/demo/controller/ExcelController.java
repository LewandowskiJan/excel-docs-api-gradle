package com.lewandowski.excel.api.demo.controller;

import com.lewandowski.excel.api.demo.dto.ExcelDto;
import com.lewandowski.excel.api.demo.repository.ExcelRepository;
import com.lewandowski.excel.api.demo.service.ExcelServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ExcelController {

    private final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    ExcelServiceDto excelServiceDto;

    @Autowired
    ExcelRepository excelRepository;

    @GetMapping("/show")
    public String showPresentList(Model model) throws IOException, GeneralSecurityException {

        logger.debug("index() is executed");

        List<ExcelDto> result = excelServiceDto.getExcelDtoList();

        System.out.println(result);

        List<ExcelDto> remoteList = new ArrayList<>();
        List<ExcelDto> holidayList = new ArrayList<>();

        int holidayCount = 0;
        int remoteCount = 0;

        for (ExcelDto excelDto : result) {
            if (excelDto.isHoliday()) {
                if (excelDto.getName().equals("")) {
                } else {
                    holidayList.add(excelDto);
                    holidayCount++;
                }
            } else {
                if (excelDto.getName().equals("")) {
                } else {
                    remoteList.add(excelDto);
                    remoteCount++;
                }
            }
        }

        model.addAttribute("holidayList", holidayList);
        model.addAttribute("remoteList", remoteList);
        model.addAttribute("holidayCount", holidayCount);
        model.addAttribute("remoteCount", remoteCount);


        return "index";

    }

}
