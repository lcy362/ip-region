package top.mobility.ip.service;

import top.mobility.ip.dto.IpDetail;
import top.mobility.ip.dto.IpSegDetail;

public interface IpCountryService {

    String getCountryOfIp(String ip);

    IpDetail getDetailOfIp(String ip);
}
