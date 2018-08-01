package com.lewandowski.excel.api.demo.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.lewandowski.excel.api.demo.dto.ExcelDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ExcelServiceDtoImpl implements ExcelServiceDto {

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = ExcelServiceDto.class.getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));


        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("72735926011-olscp2ue6oa0d3hf8qprf9jdif7000tp.apps.googleusercontent.com");
    }


    @Override
    public List<ExcelDto> getExcelDtoList() throws IOException, GeneralSecurityException {

        List<String> remoteList = new ArrayList<>();
        List<String> dayOffList = new ArrayList<>();

        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1rBHt-ZirTxoZzbyAVLptHgFo6ImQnUd0o0aNF27XF3M";
        final String range = "Arkusz1!A2:B";
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
            System.out.println("Zdalnie, Urlop");
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                //System.out.printf("%s, %s\n", row.get(0), row.get(1));
                try {
                    remoteList.add((String) row.get(0));
                } catch (Exception exp) {
                    remoteList.add("");
                }
                try {
                    dayOffList.add((String) row.get(1));
                } catch (Exception exp) {
                    dayOffList.add("");
                }
            }
        }

        List<ExcelDto> excelDtos = new ArrayList<>();

        for (String remote : remoteList) {
            ExcelDto dto = new ExcelDto();
            System.out.println(remote);
            dto.setName(remote);
            dto.setHoliday(false);
            dto.setRemote(true);
            excelDtos.add(dto);
        }

        for (String dayOff : dayOffList) {
            ExcelDto dto = new ExcelDto();
            dto.setName(dayOff);
            dto.setHoliday(true);
            dto.setRemote(false);
            excelDtos.add(dto);
        }


        System.out.println("Remote = " + remoteList.toString());
        System.out.println("Day Off = " + dayOffList.toString());

        return excelDtos;
    }

}

