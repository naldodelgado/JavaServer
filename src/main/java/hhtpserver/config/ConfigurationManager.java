package hhtpserver.config;

import com.fasterxml.jackson.databind.JsonNode;
import hhtpserver.util.Json;

import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager = null;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager(){

    }

    public static ConfigurationManager getInstance(){
        if(myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    /*
    * Used to load a configurationFile
    *   on the tutorial this is composed with an amongus load of try-catches throwing HttpConfigurationExceptions
    */
    public void loadConfigurationFile(String filepath) throws IOException {
        FileReader fileReader = new FileReader(filepath);
        StringBuffer sb = new StringBuffer();
        int i;
        while((i = fileReader.read()) != -1){
            sb.append((char) i);
        }
        JsonNode conf = Json.parse(sb.toString());
        myCurrentConfiguration = Json.fromJson(conf,Configuration.class);
    }

    /*
     * Returns the current loaded Configuration
     */
    public Configuration getConfiguration(){
        if(myCurrentConfiguration == null){
            throw new HttpConfigurationException("No Current Configuration Set.");
        }
        return myCurrentConfiguration;
    }
}
