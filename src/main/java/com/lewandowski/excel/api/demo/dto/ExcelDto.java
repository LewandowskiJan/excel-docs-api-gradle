package com.lewandowski.excel.api.demo.dto;

import org.springframework.stereotype.Component;

@Component
public class ExcelDto {

    private String name;

    public ExcelDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExcelDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
