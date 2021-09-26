package top.mobility.ip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mobility.ip.service.IpCountryService;

@SpringBootTest
class IpCountryApplicationTests {

    @Autowired
    private IpCountryService ipCountryService;

    @Test
    void contextLoads() {
    }

    @Test
    public void ipTest() {
        String x = ipCountryService.getCountryOfIp("255.255.255.255");
        System.out.println(x);
    }

}
