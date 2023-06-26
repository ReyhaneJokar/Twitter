package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A singleton class
 */

public class Config {
    private static Config instance = null;

    private final String CONFIG_FILE_NAME = "D:\\AP\\midterm-project\\6\\config.txt";
    private String FILE_NAME;
    private int PORT;

    //and other configurations

    private Config(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG_FILE_NAME))) {
            String line = bufferedReader.readLine();
            while (line != null){
                String property = line.split(":")[0];
                String value = line.split(":")[1];
                switch (property){
                    case "FILE":
                        this.FILE_NAME = value;
                        break;
                    case "PORT":
                        this.PORT = Integer.parseInt(value);
                        break;
                }
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (instance == null)
            instance = new Config();
        return instance;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public int getPORT() {
        return PORT;
    }
}
