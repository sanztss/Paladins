package phr33ze.google.com.paladins.hirezstudios;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

import phr33ze.google.com.paladins.util.CacheData;
import phr33ze.google.com.paladins.util.HttpClient;
import phr33ze.google.com.paladins.util.StringData;
import phr33ze.google.com.paladins.util.Utility;

/**
 * Created by Des. Android on 05/09/2017.
 */

public class Session extends HttpClient {
    private final String platform;
    private final String devId;
    private final String authKey;
    private Context context;

    Session(Paladins.Platform platform, String devId, String authKey, Context context) {
        super(platform.getUrl());
        this.platform = "PALADINS_" + platform.name();
        this.devId = devId;
        this.authKey = authKey;
        this.context = context;

        CacheData cacheData = new CacheData(context);
        Calendar now = Calendar.getInstance();
        long cachedTimestamp = cacheData.getLong("timestampPlus15");
        Date dateCachedTimestamp = new Date(cachedTimestamp);
        Calendar calendarCachedTimestamp = Calendar.getInstance();
        calendarCachedTimestamp.setTime(dateCachedTimestamp);
        System.out.println("----------------------------------------------");
        System.out.println("Verificando a necessidade de criar nova sessão");
        System.out.println("----------------------------------------------");
        if (cacheData.getString("session").equals("")){
            this.generateSession();
        }else if (!cacheData.getString("session").equals("") && now.getTime().after(calendarCachedTimestamp.getTime())){
            this.generateSession();
        }else {
            System.out.println("----------------------------");
            System.out.println("Aproveitando sessão em cache");
            System.out.println("----------------------------");
        }
    }

    StringData get(String endpoint, String... args) {
        System.out.println("----------------------------------------------");
        System.out.println("Verificando a necessidade de criar nova sessão");
        System.out.println("----------------------------------------------");
        return new StringData(request(Utility.generateUrl(this.platform, endpoint, devId, authKey, context), args));
    }

    void generateSession() {
        try {
            StringData data = new StringData(request("createsessionJson", devId, Utility.generateSignature(devId, authKey, "createsession"), Utility.generateTimestamp()));
            if (data.toJsonObject().getString("ret_msg").equals("Approved")) {
                System.out.println("-------------------");
                System.out.println("Criando nova sessão");
                System.out.println("-------------------");
                CacheData cacheData = new CacheData(context);
                Calendar calendarNow = Calendar.getInstance();
                long timestampNow = calendarNow.getTimeInMillis();
                Date addMinutes = new Date(timestampNow + (14 * 60000));
                Calendar calendarPlus15 = Calendar.getInstance();
                calendarPlus15.setTime(addMinutes);
                long timestampPlus15 = calendarPlus15.getTimeInMillis();
                cacheData.putLong("timestampPlus15", timestampPlus15);
                cacheData.putString("session", data.toJsonObject().getString("session_id"));
            } else throw new Exception(data.toJsonObject().getString("ret_msg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    String testSession() {
        return get("testsession").toString();
    }

    StringData getDataUsage() {
        return get("getdataused");
    }
}