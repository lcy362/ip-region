package top.mobility.ip.service;

import top.mobility.ip.dto.IpDetail;
import top.mobility.ip.dto.IpSegDetail;

public interface IpCountryService {
    IpDetail getDetailOfIp(String ip);

    String getCountryOfIp(String ip);
}
