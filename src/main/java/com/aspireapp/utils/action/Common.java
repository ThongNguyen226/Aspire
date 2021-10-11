package com.aspireapp.utils.action;

import org.openqa.selenium.support.ui.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Common {

    private static WebDriverWait wait;

    public static void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
            //Nothing
        }
    }

    public static String getRandomString(String prefix) {
        return prefix.concat(new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }

    public static String getRandomEmail(String prefix) {
        return Common.getRandomString(prefix) + "@gmail.com";
    }

    public static Object[][] readDataRoles() {
        try {
            File file = new File("src/test/java/com/aspireapp/data/dataTest_Roles.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String dataRole = "";
            String st;
            Object[][] dataRoleO = new Object[9][3];
            int i =0;
            while ((st = br.readLine()) != null) {

                System.out.println(st);
                dataRoleO[i][0] = st.split(",")[0];
                dataRoleO[i][1] = st.split(",")[1];
                if (dataRoleO[i][1].equals("Finance")) {
                    dataRoleO[i][2] = st.split(",")[2];
                }
                i++;
            }
            System.out.println(dataRole);

            return dataRoleO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
