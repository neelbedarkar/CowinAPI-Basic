import com.cowin.api.APIFunctions;
import com.cowin.api.CallAPI;
import com.cowin.bean.CalenderPincode;
import com.cowin.bean.Center;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CowinAPI {


    public static void main(String[] args) throws IOException {
        //String command = "powershell.exe  your command";
        //Getting the version
        String command = "powershell.exe  Invoke-RestMethod 'https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=294&date=08-05-2021' -Method 'GET' | ConvertTo-Json" ;
        // Executing the command
        Process powerShellProcess = Runtime.getRuntime().exec(command);
        // Getting the results
        powerShellProcess.getOutputStream().close();
        String line;
        System.out.println("Standard Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                powerShellProcess.getInputStream()));
        StringBuffer sb = new StringBuffer();
        while ((line = stdout.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());

        stdout.close();
        Gson gsonObj = new Gson();
        CalenderPincode compiledData = gsonObj.fromJson(sb.toString(), CalenderPincode.class);
        List<Center> ageGroupData = compiledData.getCenters().stream().filter(p -> p.getSessions().stream()
                .anyMatch(s -> s.getMinAgeLimit() < 45)).collect(Collectors.toList());
        System.out.println("Number of Centres with Sessions for 18+:" + ageGroupData.size());
        List<Center> requiredData = ageGroupData.stream().filter(p -> p.getSessions().stream()
                .anyMatch(s -> s.getMinAgeLimit() < 45 && s.getAvailableCapacity()>0)).collect(Collectors.toList());
        System.out.println("Number of Centres with Sessions for 18+ Available:" + requiredData.size());
        System.out.println("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
        stderr.close();
        System.out.println("Done");

    }
}

