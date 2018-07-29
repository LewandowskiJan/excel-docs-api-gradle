package com.lewandowski.excel.api.demo.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.lewandowski.excel.api.demo.auth.SheetsQuickstart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static com.lewandowski.excel.api.demo.auth.SheetsQuickstart.JSON_FACTORY;
import static com.lewandowski.excel.api.demo.auth.SheetsQuickstart.getCredentials;

@Service
public class ExcelServiceImpl implements ExcelService {

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";

    @Autowired
    SheetsQuickstart sheetsQuickstart;

    @Override
    public List<List<Object>> getDataFormWorkbook() throws IOException, GeneralSecurityException {

        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1rBHt-ZirTxoZzbyAVLptHgFo6ImQnUd0o0aNF27XF3M";
        final String range = "Arkusz1!A2:E";
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                System.out.printf("%s, %s\n", row.get(0), row.get(4));
            }
        }
        return values;
    }
}
