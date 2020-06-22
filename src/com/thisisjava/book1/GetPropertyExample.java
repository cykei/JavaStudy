package com.thisisjava.book1;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Properties;
import java.util.Set;

public class GetPropertyExample {
    public static void main(String[] args){
        Properties props = System.getProperties();
        Set keys = props.keySet();
        for (Object objkey : keys){
            String key = (String) objkey;
            String value = System.getProperty(key);
            System.out.println("["+key+"]  "+value);
        }
    }
}
