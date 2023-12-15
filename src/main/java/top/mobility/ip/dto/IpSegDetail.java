package top.mobility.ip.dto;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import top.mobility.ip.util.IpUtils;

@Data
public class IpSegDetail {

    private String start;

    private String end;

    private String countryCode;

    private String country;

    private String region;

    private String city;

    private String latitude;

    private String longitude;

    private String zipCode;

    private String timeZone;


    public IpSegDetail(CSVRecord csvRecord) {
//        this.start = IpUtils.convertNumericIP(csvRecord.get(0));
//        this.end = IpUtils.convertNumericIP(csvRecord.get(1));
        this.start = csvRecord.get(0);
        this.end = csvRecord.get(1);
        this.countryCode = csvRecord.get(2);
        this.country = csvRecord.get(3);
        this.region = csvRecord.get(4);
        this.city = csvRecord.get(5);
        this.latitude = csvRecord.get(6);
        this.longitude = csvRecord.get(7);
        this.zipCode = csvRecord.get(8);
        this.timeZone = csvRecord.get(9);
    }

    public boolean isCivil() {
        return StringUtils.equalsIgnoreCase(country, "China");
    }

    public String getOutPut() {
        return start + "," + end + "," + country + "," + region + "," + city + "," + latitude + "," + longitude + "," + zipCode + "," + timeZone;
    }
    public String getOutPutWithoutCountry() {
        return start + "," + end + "," + region + "," + city + "," + latitude + "," + longitude + "," + zipCode + "," + timeZone;
    }

    public String getSimpleOut() {
        return start + "," + countryCode + "," + region + "," + city;
    }
}
