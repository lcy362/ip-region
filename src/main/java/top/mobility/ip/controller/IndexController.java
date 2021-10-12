package top.mobility.ip.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.mobility.ip.service.IpCountryService;
import top.mobility.ip.util.IpUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final IpCountryService service;

    @GetMapping({"/index", "/"})
    public String showPage(Model model, String ip, HttpServletRequest request) {
        if (ip != null) {
            model.addAttribute("result", service.getCountryOfIp(ip));
        }
        String ownIp = IpUtils.getClientIpAddress(request);
        String country = service.getCountryOfIp(ownIp);
        log.info("get request ip = {}, country = {}", ownIp, country);
        model.addAttribute("ownip", ownIp);
        model.addAttribute("country", country);
        return "index";
    }

}
