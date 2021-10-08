package top.mobility.ip.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mobility.ip.service.IpCountryService;
import top.mobility.ip.util.IpUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ip-country/address/")
@RequiredArgsConstructor
@Slf4j
public class IpCountryController {

    private final IpCountryService service;

    @GetMapping("/country")
    public String getCountry(HttpServletRequest request) {
        String ip = IpUtils.getClientIpAddress(request);
        return service.getCountryOfIp(ip);
    }
}
