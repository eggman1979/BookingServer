package util;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DebugLog {
	
	private static Logger logger;
	private static FileHandler fh;
	
	public static void log(String msg) {  
		String catalina = "";
		try {  
			Logger logger = Logger.getLogger("MyLog");  
			// This block configure the logger with handler and formatter 
			if(System.getProperty("os.name").contains("Windows")){
				catalina = System.getenv("CATALINA_HOME");
			}else{
				 catalina = "/home/afgproj_vasketider_kim_s144842/apache-tomcat-8.5.24";
			}
			
			 fh = new FileHandler(catalina + "/logs/log.txt", true);  
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  

			// the following statement is used to log any messages  
			logger.info(msg);

		} catch (SecurityException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  finally{
			fh.close();
		}

	
		

	}
	public void closeFileHandler(){
		fh.close();
	}
}