package top.mobility.ip.service;

import com.google.common.net.InetAddresses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import top.mobility.ip.dto.IpDetail;
import top.mobility.ip.dto.IpSegDetail;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.List;

@Service
@Slf4j
public class IpCountryServiceImpl implements IpCountryService{

    private static long[] detailSegStartPoint;
    private static IpDetail[] detailSegDesc;

    @PostConstruct
    public void init() throws IOException {
        ClassPathResource resource = new ClassPathResource("data/ipv4-detail.data");
        InputStream inputStream = resource.getInputStream();
        List<String> lines = IOUtils.readLines(inputStream, "UTF-8");
        detailSegStartPoint = new long[lines.size()];
        detailSegDesc = new IpDetail[lines.size()];
        int i = 0;
        for (String line : lines) {
            String[] split = line.split(",");
            detailSegStartPoint[i] = Long.parseLong(split[0]);
            detailSegDesc[i++] = new IpDetail(split);
        }
    }

    @Override
    public IpDetail getDetailOfIp(String ip) {
        InetAddress inetAddress = InetAddresses.forString(ip);
        long ipValue = InetAddresses.toBigInteger(inetAddress).longValue();
        int i = 0;
        if (ipValue == 0) {
            return detailSegDesc[0];
        }
        int j = detailSegStartPoint.length - 1;
        if (ipValue > detailSegStartPoint[j]) {
            return detailSegDesc[j];
        }
        while (i < j) {
            int h = (i + j) / 2;
            if (detailSegStartPoint[h] > ipValue) {
                j = h;
            } else {
                i = h + 1;
            }
        }

        return detailSegDesc[i - 1];
    }


}
