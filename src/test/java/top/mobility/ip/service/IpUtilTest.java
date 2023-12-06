package top.mobility.ip.service;

import org.junit.jupiter.api.Test;

import static top.mobility.ip.util.IpUtils.convertNumericIP;

public class IpUtilTest {

    @Test
    public void convertTest() {
        String numericIP = "625531393";
        String dottedDecimalIP = convertNumericIP(numericIP);
        System.out.println(dottedDecimalIP); // 输出：0.0.0.0

    }
}
