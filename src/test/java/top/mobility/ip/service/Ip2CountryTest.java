package top.mobility.ip.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Ip2CountryTest {

    @Autowired
    private IpCountryService service;

    @Test
    public void ipCountryTest() {
        Assertions.assertEquals("", service.getCountryOfIp(""));
        Assertions.assertEquals("-", service.getCountryOfIp("0.0.0.0"));
        Assertions.assertEquals("-", service.getCountryOfIp("0.0.0.1"));
        Assertions.assertEquals("US", service.getCountryOfIp("1.0.0.0"));
        Assertions.assertEquals("US", service.getCountryOfIp("1.1.1.1"));
        Assertions.assertEquals("CN", service.getCountryOfIp("121.229.143.64"));
        Assertions.assertEquals("CN", service.getCountryOfIp("122.96.43.186"));
        Assertions.assertEquals("CN", service.getCountryOfIp("153.3.123.160"));
        Assertions.assertEquals("CN", service.getCountryOfIp("153.3.131.201"));
        Assertions.assertEquals("CN", service.getCountryOfIp("180.109.81.198"));
        Assertions.assertEquals("CN", service.getCountryOfIp("180.111.103.88"));
        Assertions.assertEquals("CN", service.getCountryOfIp("183.206.11.225"));
        Assertions.assertEquals("US", service.getCountryOfIp("192.210.171.249"));
        Assertions.assertEquals("CN", service.getCountryOfIp("223.112.9.2"));
        Assertions.assertEquals("CA", service.getCountryOfIp("23.16.28.232"));
        Assertions.assertEquals("CN", service.getCountryOfIp("58.240.115.210"));
        Assertions.assertEquals("CN", service.getCountryOfIp("61.155.4.66"));
        Assertions.assertEquals("AU", service.getCountryOfIp("223.255.255.254"));
        Assertions.assertEquals("-", service.getCountryOfIp("255.255.255.254"));
        Assertions.assertEquals("-", service.getCountryOfIp("255.255.255.255"));
    }
}
