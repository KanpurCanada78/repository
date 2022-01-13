package com.twilioTests;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println(Timestamp.from(Instant.now()).);
		
		
		 long currentTimestamp = System.currentTimeMillis();
		 System.out.println("Current epoch timestamp in millis: " + currentTimestamp);
		 currentTimestamp = Instant.now().toEpochMilli();
		 System.out.println("Current epoch timestamp in millis: " + currentTimestamp);
		 

        Date today = Calendar.getInstance().getTime();
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        System.out.println(simpleDateFormat.format(today));
        System.out.println(Long.valueOf("180000"));
	}

}
