/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Zaw Thet
 */
public class DatabaseConfigManager {
    
    public void saveDatabaseProperties(DatabaseProperty dbProperty){
        
        Properties prop = new Properties();
		
		try(OutputStream os = new FileOutputStream("dbConfig.properties")) {
			
		prop.setProperty("host",dbProperty.getHost());
                prop.setProperty("port",dbProperty.getPort());
		prop.setProperty("username",dbProperty.getUser());	
		prop.setProperty("password",dbProperty.getPassword());	
		
		prop.store(os, "For Database Configuration");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    
    public DatabaseProperty getDatabaseProperties(){
        Properties prop = new Properties();
	
        DatabaseProperty dbProperty = null;
        try(InputStream is = new FileInputStream("dbConfig.properties")) {

        prop.load(is);
        
        String host = prop.getProperty("host");
        String port = prop.getProperty("port");
        String user = prop.getProperty("username");
        String password = prop.getProperty("password");
        
        dbProperty = new DatabaseProperty(host,port,user,password);
        
		} catch (Exception e) {
			e.printStackTrace();
		}
        return dbProperty;
    }
}
