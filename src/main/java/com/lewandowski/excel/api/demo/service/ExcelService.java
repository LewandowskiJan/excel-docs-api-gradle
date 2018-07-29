package com.lewandowski.excel.api.demo.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface ExcelService {

    List<List<Object>> getDataFormWorkbook() throws IOException, GeneralSecurityException;

}
