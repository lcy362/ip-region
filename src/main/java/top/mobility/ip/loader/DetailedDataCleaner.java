package top.mobility.ip.loader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.mobility.ip.dto.IpSegDetail;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

//@Component
@Slf4j
public class DetailedDataCleaner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ZipFile input = new ZipFile("/Users/lcy/Downloads/IP2LOCATION-LITE-DB11.CSV.zip");
        Enumeration<? extends ZipEntry> entries = input.entries();

        List<String> linesAll = new ArrayList<>();
        List<String> linesCivil = new ArrayList<>();

        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            if (entry.getName().equals("IP2LOCATION-LITE-DB11.CSV")) {
                InputStream stream = input.getInputStream(entry);
                BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                CSVParser parser = new CSVParser(br, CSVFormat.DEFAULT);
                for (CSVRecord csvRecord : parser) {
                    log.info(csvRecord.toString());
                    IpSegDetail detail = new IpSegDetail(csvRecord);
                    linesAll.add(detail.getOutPut());
                    if (detail.isCivil()) {
                        linesCivil.add(detail.getOutPutWithoutCountry());
                    }

                }
            }
        }
//        FileUtils.writeLines(new File("detail-civil.csv"), linesCivil);
        log.info("finish civil");

        FileUtils.writeLines(new File("ipv4-detail.data"), linesAll);
        log.info("finish");

    }
}
