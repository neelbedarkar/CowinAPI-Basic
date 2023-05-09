package com.cowin.api;

import com.cowin.bean.CalenderPincode;
import com.cowin.bean.Center;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class APIFunctions {
    private static final String CALENDER_PIN_URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode={0}&date={1}";
    private static final String CALENDER_DISTRICT_URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id={0}&date={1}";

    public static boolean pinCodeCalenderHasSessions(String pinCode,String date) throws IOException {
        URL url = new URL(MessageFormat.format(CALENDER_PIN_URL, pinCode, date));
        URLConnection urlConn = url.openConnection();
        HttpsURLConnection httpsConn = (HttpsURLConnection) urlConn;
        httpsConn.setRequestProperty("accept","application/json");
        httpsConn.setRequestMethod("GET");
        httpsConn.setDoOutput(true);
        httpsConn.setDoInput(true);
        System.out.println("Sessions by PinCode: " + pinCode);
        List<Center> requiredData = getAllowedCenters(httpsConn);
        httpsConn.disconnect();
        return requiredData.size()>0;
    }
    public static boolean districtCalenderHasSessions(String district,String date) throws IOException {
        URL url = new URL(MessageFormat.format(CALENDER_DISTRICT_URL, district, date));
        URLConnection urlConn = url.openConnection();
        HttpURLConnection httpsConn = (HttpURLConnection) urlConn;
        //httpsConn.setRequestProperty("accept","application/json");
        httpsConn.setRequestProperty("Accept-Language","en_US");

        httpsConn.setRequestMethod("GET");
        httpsConn.setDoOutput(true);
        httpsConn.setDoInput(true);
        System.out.println("Sessions by District: " + district);
        List<Center> requiredData = getAllowedCenters(httpsConn);
        httpsConn.disconnect();
        return requiredData.size()>0;
    }

    private static List<Center> getAllowedCenters(HttpURLConnection httpsConn) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(httpsConn.getInputStream(), baos);
        Gson gsonObj = new Gson();
        CalenderPincode compiledData = gsonObj.fromJson(baos.toString("UTF-8"), CalenderPincode.class);
        List<Center> ageGroupData = compiledData.getCenters().stream().filter(p -> p.getSessions().stream()
                .anyMatch(s -> s.getMinAgeLimit() < 45)).collect(Collectors.toList());
        System.out.println("Number of Centres with Sessions for 18+:" + ageGroupData.size());
        List<Center> requiredData = ageGroupData.stream().filter(p -> p.getSessions().stream()
                .anyMatch(s -> s.getMinAgeLimit() < 45 && s.getAvailableCapacity()>0)).collect(Collectors.toList());
        System.out.println("Number of Centres with Sessions for 18+ Available:" + requiredData.size());
        return requiredData;
    }
}
