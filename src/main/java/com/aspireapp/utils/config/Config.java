package com.aspireapp.utils.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

public class Config {

    private String invocationId;

    @ConfigParameter(required = true)
    private static String baseurl = "https://feature-qa-automation.customer-frontend.staging.aspireapp.com/sg/";

    @ConfigParameter
    private static String adminUser = "+84989677728";

    @ConfigParameter
    private static String adminPin = "135790";

    @ConfigParameter
    private static boolean isHeadless = true;

    @ConfigParameter
    private String ChromeDriverPath = "null";


    public Config(File file, Map<String, String> overrides) throws ConfigException {

        invocationId = UUID.randomUUID().toString().substring(0, 5);

        Properties fileProps = loadProperties(file);
        final Set<Map.Entry<String, String>> entrySet = overrides.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            final Object o = fileProps.get(entry.getKey());
            if (o != null) {
                System.out.println("Overriding config for [" + entry.getKey() + "] from [" + o + "] with [" + entry.getValue() + "]");
            }
            fileProps.put(entry.getKey(), entry.getValue());
        }


        Field[] fields = this.getClass().getDeclaredFields();
        for (int x = 0; x < fields.length; x++) {
            Field f = fields[x];
            if (f.isAnnotationPresent(ConfigParameter.class)) {
                ConfigParameter cp = f.getAnnotation(ConfigParameter.class);
                String value = (String) fileProps.remove(f.getName());
                if (cp.required() && value == null) {
                    throw new ConfigException("[" + f.getName() + "] is a required configuration parameter.");
                }
                if (value != null) {
                    value = value.trim();
                    try {
                        if (String.class.equals(f.getType())) {
                            f.set(this, value);
                        } else if (Integer.class.equals(f.getType()) || int.class.equals(f.getType())) {
                            f.set(this, getInteger(f.getName(), value));
                        } else if (Boolean.class.equals(f.getType()) || boolean.class.equals(f.getType())) {
                            f.set(this, getBoolean(f.getName(), value));
                        } else {
                            throw new ConfigException("[" + f.getName() + "] Unsupported Type");
                        }
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    private static Properties loadProperties(File file) throws ConfigException {
        Properties prop = new Properties();
        if (file == null) {
            return prop;
        }
        if (!file.exists()) {
            throw new ConfigException("Configuration file doesn't exist: " + file.getAbsolutePath());
        }
        try (InputStream input = new FileInputStream(file)) {
            prop.load(input);
        } catch (IOException e) {
            throw new ConfigException("Unable to load config file: " + e.getMessage());
        }
        return prop;
    }

    private Integer getInteger(String name, String o) throws ConfigException {
        try {
            return Integer.valueOf((String) o);
        } catch (NumberFormatException e) {
            throw new ConfigException(name + " should be an integer parameter : " + o);
        }
    }

    private boolean getBoolean(String name, String o) throws ConfigException {
        final String cleaned = o.toLowerCase().trim();
        if (cleaned.equals("true")) {
            return true;
        } else if (cleaned.equals("false")) {
            return false;
        }
        throw new ConfigException(name + " should be an either \"true\" or \"false\" but was " + o);
    }

    public static String getBaseUrl() {
        return baseurl;
    }

    public static String getAdminUser() {
        return adminUser;
    }

    public static String getAdminPin() {
        return adminPin;
    }

    public static boolean isHeadless() {
        return isHeadless;
    }


    public static String getProfileFolder(String SubFolder) {
        String folderPath = System.getProperty("user.dir");
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            folderPath = folderPath + "\\profiles\\" + SubFolder;
        } else {
            folderPath = folderPath + "/profiles/" + SubFolder;
        }
        File file = new File(folderPath);
        if (file.exists()) {
            cleanDirectory(file);
            file.delete();
        }

        file.mkdir();

        return folderPath;
    }

    public static String getChromeDriverPath() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            return "lib\\chromedriver_win32\\chromedriver.exe";
        } else {
            return "lib/chromedriver_linux64/chromedriver";
        }
    }

    public static void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }

    public static void cleanDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
        }
    }

}
