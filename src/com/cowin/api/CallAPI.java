package com.cowin.api;

import com.cowin.bean.CalenderPincode;
import com.cowin.bean.Center;
import com.cowin.util.SoundUtils;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.sound.sampled.LineUnavailableException;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CallAPI {

    public static void main(String args[]) {
        try {
           // URL url = new URL("file:///C:\\Users\\Sunita Mandrupkar\\Documents\\Code Repositories\\Cowin\\sounds\\dive alarm.wav");
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = df.format(new Date());
            APIFunctions.districtCalenderHasSessions("294", currentDate);
            URL url = new URL("");
            AudioClip clip = Applet.newAudioClip(url);
           // clip.play();
            try {
/*                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String currentDate = df.format(new Date());*/

                while (true) {
                    if (APIFunctions.pinCodeCalenderHasSessions("560037", currentDate)) {
                        System.out.println("Found Session in Marathahalli");
                        clip.play();
                    }  if (APIFunctions.pinCodeCalenderHasSessions("560103", currentDate)) {
                        System.out.println("Found Session in Bellandur");
                        clip.play();
                    } if (APIFunctions.pinCodeCalenderHasSessions("560034", currentDate)) {
                        System.out.println("Found Session in Koramangla");
                        clip.play();
                    }
                     if (APIFunctions.pinCodeCalenderHasSessions("560030", currentDate)) {
                        System.out.println("Found Session in Koramangla 560030");
                        clip.play();
                    }
                     if (APIFunctions.pinCodeCalenderHasSessions("560038", currentDate)) {
                        System.out.println("Found Session in Indiranagar");
                        clip.play();
                    }
                     if (APIFunctions.pinCodeCalenderHasSessions("562125", currentDate)) {
                        System.out.println("Found Session in Sarjapur");
                        clip.play();
                    }
                     if (APIFunctions.pinCodeCalenderHasSessions("560066", currentDate)) {
                       System.out.println("Found Session in Sarjapur");
                        clip.play();
                    }
                    if (APIFunctions.pinCodeCalenderHasSessions("560052", currentDate)) {
                        System.out.println("Found Session in Central Bangalore");
                        clip.play();
                    }
                     if (APIFunctions.districtCalenderHasSessions("265", currentDate)){
                        System.out.println("Found Session in Urban Bangalore");
                        clip.play();
                    }
                     if (APIFunctions.districtCalenderHasSessions("294", currentDate)){
                        System.out.println("Found Session in BBMP");
                        clip.play();
                    }
                    System.out.println(new Date());
                    Thread.sleep(60000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                clip.play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
