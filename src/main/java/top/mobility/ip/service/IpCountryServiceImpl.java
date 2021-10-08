package top.mobility.ip.service;

import com.google.common.net.InetAddresses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

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

    private static long[] ipv4SegStartPoint;
    private static String[] ipv4SegDesc;

    private static BigInteger[] ipv6SegStartPoint;
    private static String[] ipv6SegDesc;

    @PostConstruct
    public void init() throws IOException {
        ClassPathResource resource = new ClassPathResource("data/ipv4-country.data");
        InputStream inputStream = resource.getInputStream();
        List<String> lines = IOUtils.readLines(inputStream, "UTF-8");
        ipv4SegStartPoint = new long[lines.size()];
        ipv4SegDesc = new String[lines.size()];
        int i = 0;
        for (String line : lines) {
            String[] split = line.split(",");
            ipv4SegStartPoint[i] = Long.parseLong(split[0]);
            ipv4SegDesc[i++] = split[1];
        }

        resource = new ClassPathResource("data/ipv6-country.data");
        inputStream = resource.getInputStream();
        lines = IOUtils.readLines(inputStream, "UTF-8");
        ipv6SegStartPoint = new BigInteger[lines.size()];
        ipv6SegDesc = new String[lines.size()];
        i = 0;
        for (String line : lines) {
            String[] split = line.split(",");
            ipv6SegStartPoint[i] = new BigInteger(split[0]);
            ipv6SegDesc[i++] = split[1];
        }
    }

    @Override
    public String getCountryOfIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        InetAddress inetAddress = InetAddresses.forString(ip);
        if (inetAddress instanceof Inet4Address) {
            long ipValue = InetAddresses.toBigInteger(inetAddress).longValue();
            int i = 0;
            if (ipValue == 0) {
                return ipv4SegDesc[0];
            }
            int j = ipv4SegStartPoint.length - 1;
            if (ipValue > ipv4SegStartPoint[j]) {
                return ipv4SegDesc[j];
            }
            while (i < j) {
                int h = (i + j) / 2;
                if (ipv4SegStartPoint[h] > ipValue) {
                    j = h;
                } else {
                    i = h + 1;
                }
            }

            return ipv4SegDesc[i - 1];
        } else if (inetAddress instanceof Inet6Address) {
            BigInteger ipValue = InetAddresses.toBigInteger(inetAddress);
            int i = 0;
            if (ipValue.intValue() == 0) {
                return ipv6SegDesc[0];
            }
            int j = ipv6SegStartPoint.length - 1;
            if (ipValue.compareTo(ipv6SegStartPoint[j]) > 0) {
                return ipv6SegDesc[j];
            }
            while (i < j) {
                int h = (i + j) / 2;
                if (ipv6SegStartPoint[h].compareTo(ipValue) > 0) {
                    j = h;
                } else {
                    i = h + 1;
                }
            }

            return ipv6SegDesc[i - 1];
        }
        return "";
    }


}
