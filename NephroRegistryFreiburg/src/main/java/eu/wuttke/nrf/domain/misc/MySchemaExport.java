package eu.wuttke.nrf.domain.misc;

import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.EnversSchemaGenerator;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class MySchemaExport {

	public static void main(String[] args) {
		//execute("persistenceUnit", "/tmp/schema.sql", true, true);
		envers();
	}
	
	public static void execute(String persistenceUnitName, String destination, boolean create, boolean format) {
		System.out.println("Starting schema export");
		Ejb3Configuration cfg = new Ejb3Configuration().configure(persistenceUnitName, new Properties());
		Configuration hbmcfg = cfg.getHibernateConfiguration();
		SchemaExport schemaExport = new SchemaExport(hbmcfg);
		schemaExport.setOutputFile(destination);
		schemaExport.setFormat(format);
		schemaExport.execute(true, false, false, create);
		System.out.println("Schema exported to " + destination);
	}
	
	public static void envers() {
		Ejb3Configuration cfg = new Ejb3Configuration().configure("persistenceUnit", new Properties());
		Configuration hbmcfg = cfg.getHibernateConfiguration();
		SchemaExport export = new EnversSchemaGenerator(hbmcfg).export();
		export.setOutputFile("/tmp/schema.sql");
		export.execute(true, false, false, false);		
	}

}
