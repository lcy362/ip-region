package top.mobility.ip.dto;

import lombok.Data;

@Data
public class IpDetail {

    private String countryCode;

    private String country;

    private String region;

    private String city;

    private String latitude;

    private String longitude;

    private String zipCode;

    private String timeZone;

    public IpDetail(String[] split) {
//        this.countryCode = split[2];
        this.country = split[2];
        this.region = split[3];
        this.city = split[4];
        this.latitude = split[5];
        this.longitude = split[6];
        this.zipCode = split[7];
        this.timeZone = split[8];
    }
}
