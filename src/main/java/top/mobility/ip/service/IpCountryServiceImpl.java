package top.mobility.ip.service;

import com.google.common.net.InetAddresses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Service
@Slf4j
public class IpCountryServiceImpl implements IpCountryService{

    private static long[] segStartPoint;
    private static String[] segDesc;

    @PostConstruct
    public void init() throws IOException {
        List<String> lines = FileUtils.readLines(new File("ip-country.data"), "UTF-8");
        segStartPoint = new long[lines.size()];
        segDesc = new String[lines.size()];
        int i = 0;
        for (String line : lines) {
            String[] split = line.split(",");
            segStartPoint[i] = Long.parseLong(split[0]);
            segDesc[i++] = split[1];
        }
    }

    @Override
    public String getCountryOfIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        InetAddress inetAddress = InetAddresses.forString(ip);
        long ipValue = InetAddresses.toBigInteger(inetAddress).longValue();
        int i = 0;
        if (ipValue == 0) {
            return segDesc[0];
        }
        int j = segStartPoint.length - 1;
        if (ipValue > segStartPoint[j]) {
            return segDesc[j];
        }
        while (i < j) {
           int h = (i + j) / 2;
            if (segStartPoint[h] > ipValue) {
                j = h;
            } else {
                i = h + 1;
            }
        }

        return segDesc[i - 1];
    }


}
