package com.isi.appmon.agent;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by samurdiw on 1/24/2017.
 */
public class Settings {
    private static Properties properties;

    static{
        init();
    }
    private static void init() {
        properties = new Properties();
        InputStream input = null;

        try {

            input = Settings.class.getResourceAsStream("/META-INF/AgentConfigBasic.properties");
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
         return properties.get(key)+"";
    }


}
