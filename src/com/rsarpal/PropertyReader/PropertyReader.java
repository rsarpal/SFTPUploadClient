package com.rsarpal.PropertyReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private String propertyFilePath;
    private FileInputStream inputStream;
    private Properties prop;
    private String propertyValue;

    public PropertyReader(String propertyFilePath) {
        this.propertyFilePath=propertyFilePath;

        //Check if file exists and initialise file pointer
        try {
            this.inputStream = new FileInputStream(this.propertyFilePath);
        }catch (FileNotFoundException fe){
            System.out.println("PropertyReader: File not Found" + propertyFilePath);
            fe.printStackTrace();
        }

        //initialize value
        this.propertyValue="";
        initialize();

    }

    private void initialize(){
        //initialise properties
        this.prop= new Properties();
        try {
            this.prop.load(this.inputStream);
        }catch (IOException ie){
            System.out.println("PropertyReader: Couldn't Load Property File" + propertyFilePath);
            ie.printStackTrace();
        }
    }

    public String getPropertyValue(String key) {
        return this.prop.getProperty(key);
    }
}
