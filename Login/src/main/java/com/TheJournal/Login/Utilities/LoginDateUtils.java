package com.TheJournal.Login.Utilities;

public class LoginDateUtils {


    public static long getUnixTimestampNow(){
        return System.currentTimeMillis()/1000L;
    }
}
