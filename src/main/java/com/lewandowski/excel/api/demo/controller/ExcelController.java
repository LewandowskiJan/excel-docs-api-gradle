package com.lewandowski.excel.api.demo.controller;

import com.google.api.client.util.DateTime;
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
import java.sql.Date;
import java.time.LocalDate;
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

        ExcelDto dtoHolidayEmpty = new ExcelDto();
        dtoHolidayEmpty.setName("");
        dtoHolidayEmpty.setDate("");
        dtoHolidayEmpty.setHoliday(true);

        ExcelDto dtoRemoteEmpty = new ExcelDto();
        dtoRemoteEmpty.setName("");
        dtoRemoteEmpty.setDate("");
        dtoRemoteEmpty.setRemote(true);

        //System.out.println(result);

        List<ExcelDto> remoteList = new ArrayList<>();
        List<ExcelDto> holidayList = new ArrayList<>();

        int holidayCellCount = 0;
        int remoteCellCount = 0;

        for (ExcelDto excelDto : result) {
            if (excelDto.isHoliday()) {
                if (excelDto.getName().equals("")) {
                } else {
                    holidayList.add(excelDto);
                    holidayCellCount++;
                }
            } else {
                if (excelDto.getName().equals("")) {
                } else {
                    remoteList.add(excelDto);
                    remoteCellCount++;
                }
            }
        }
/*
        if (holidayCellCount%6!=0){
            while(holidayCellCount%6!=0){
                holidayList.add(dtoHolidayEmpty);
                holidayCellCount++;
            }
        }

        if (remoteCellCount%6!=0){
            while(remoteCellCount%6!=0){
                remoteList.add(dtoRemoteEmpty);
                remoteCellCount++;
            }
        }
*/
        List<ExcelDto> remoteList1 = new ArrayList<>();
        List<ExcelDto> remoteList2 = new ArrayList<>();
        List<ExcelDto> remoteList3 = new ArrayList<>();
        List<ExcelDto> remoteList4 = new ArrayList<>();
        List<ExcelDto> remoteList5 = new ArrayList<>();

        int remoteCount = 0;
        if (remoteList.size() > 0) {
            for (ExcelDto excelDto : remoteList) {

                if (remoteCount >= 0 && remoteList1.size() < 6) {
                    remoteList1.add(excelDto);
                }
                if (remoteCount >= 6 && remoteList2.size() < 6) {
                    remoteList2.add(excelDto);
                }
                if (remoteCount >= 12 && remoteList3.size() < 6) {
                    remoteList3.add(excelDto);
                }
                if (remoteCount >= 18 && remoteList4.size() < 6) {
                    remoteList4.add(excelDto);
                }
                if (remoteCount >= 24 && remoteList5.size() < 6) {
                    remoteList5.add(excelDto);
                }
                remoteCount++;
            }

        }


        List<ExcelDto> holidayList1 = new ArrayList<>();
        List<ExcelDto> holidayList2 = new ArrayList<>();
        List<ExcelDto> holidayList3 = new ArrayList<>();
        List<ExcelDto> holidayList4 = new ArrayList<>();
        List<ExcelDto> holidayList5 = new ArrayList<>();

        int holidayCount = 0;
        if (holidayList.size() > 0) {
            for (ExcelDto excelDto : holidayList) {

                if (holidayCount >= 0 && holidayList1.size() < 6) {
                    holidayList1.add(excelDto);
                }
                if (holidayCount >= 6 && holidayList2.size() < 6) {
                    holidayList2.add(excelDto);
                }
                if (holidayCount >= 12 && holidayList3.size() < 6) {
                    holidayList3.add(excelDto);
                }
                if (holidayCount >= 18 && holidayList4.size() < 6) {
                    holidayList4.add(excelDto);
                }
                if (holidayCount >= 24 && holidayList5.size() < 6) {
                    holidayList5.add(excelDto);
                }
                holidayCount++;
            }

        }


        Date dateTime = (Date.valueOf(LocalDate.now()));


        model.addAttribute("now", dateTime);

        model.addAttribute("holidayCount", holidayCellCount);
        model.addAttribute("remoteCount", remoteCellCount);

        model.addAttribute("remoteList1", remoteList1);
        model.addAttribute("remoteList2", remoteList2);
        model.addAttribute("remoteList3", remoteList3);
        model.addAttribute("remoteList4", remoteList4);
        model.addAttribute("remoteList5", remoteList5);


        model.addAttribute("holidayList1", holidayList1);
        model.addAttribute("holidayList2", holidayList2);
        model.addAttribute("holidayList3", holidayList3);
        model.addAttribute("holidayList4", holidayList4);
        model.addAttribute("holidayList5", holidayList5);


        return "index";

    }

}
