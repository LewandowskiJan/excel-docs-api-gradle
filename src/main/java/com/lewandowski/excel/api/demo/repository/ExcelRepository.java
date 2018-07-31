package com.lewandowski.excel.api.demo.repository;

import com.lewandowski.excel.api.demo.dto.ExcelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRepository extends JpaRepository<ExcelDto, Long> {
}
