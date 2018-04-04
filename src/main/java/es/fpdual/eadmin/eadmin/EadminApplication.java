package es.fpdual.eadmin.eadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EadminApplication {
	
	private static final Logger Logger = LoggerFactory.getLogger(EadminApplication.class);

	public static void main(String[] args) {
		Logger.info("Esto es un prueba");
		
		//Debug
		Logger.debug("Depuracion");
		
		//Informacion
		Logger.info("Informacion");
		
		//Traza
		Logger.trace("Traza");
		
		//Warning
		Logger.warn("Advertencia");
		
		//Error
		Logger.error("Error");
		Logger.info("Inicio Run");
		SpringApplication.run(EadminApplication.class, args);
		Logger.info("Fin Run");
	}
}
