package top.mobility.ip.loader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//@Component
@Slf4j
public class Ipv6DataRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("start load ipv6");
        String url = "http://download.ip2location.com/lite/IP2LOCATION-LITE-DB1.IPV6.CSV.ZIP";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        ZipInputStream zipStream = new ZipInputStream(response.getEntity().getContent());
        ZipEntry entry = null;
        List<String> linesInUse = new ArrayList<>();
        while ((entry = zipStream.getNextEntry()) != null) {
            if (entry.getName().equals("IP2LOCATION-LITE-DB1.IPV6.CSV")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(zipStream, "UTF-8"));
                BigInteger previousEnd = BigInteger.valueOf(-1);

                CSVParser parser = new CSVParser(br, CSVFormat.DEFAULT);
                for (CSVRecord csvRecord : parser) {
                    log.info(csvRecord.toString());
                    BigInteger start = new BigInteger(csvRecord.get(0));
                    BigInteger end = new BigInteger(csvRecord.get(1));
                    String shortName = csvRecord.get(2);
                    if (!start.equals(previousEnd.add(BigInteger.ONE)) ) {
                        BigInteger date = previousEnd.add(BigInteger.ONE);
                        linesInUse.add(date + "," + "-");
                        log.info("one mock data " + start + "_" + previousEnd);

                    }
                    linesInUse.add(start + "," + shortName);

                    previousEnd = end;
                }

            }
        }

        zipStream.close();
        FileUtils.writeLines(new File("ipv6-country.data"), linesInUse);
        log.info("finish load ipv6");
    }
}
