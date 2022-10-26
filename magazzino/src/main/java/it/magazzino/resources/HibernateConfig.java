package it.magazzino.resources;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.magazzino.entity.*;

public class HibernateConfig {

	private static SessionFactory sessionFactory;

	// Creiamo una configurazione per hibernate, impostando tutti i dati del db e le
	// classi che hibernate dovrà considerare come Entity
	// Ci ritorna una un oggetto Configuration
	private Configuration config() {
		Properties prop = new Properties();

		prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/gestionemagazzino");
		prop.setProperty("dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		prop.setProperty("hibernate.connection.username", "root");
		prop.setProperty("hibernate.connection.password", "Youtube99!");
		prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");

		Configuration config = new Configuration();
		config.addProperties(prop);
		config.addAnnotatedClass(Prodotto.class);
		config.addAnnotatedClass(Categoria.class);
		config.addAnnotatedClass(Listino.class);
		return config;
	}

	// Crea una sessione unica basata sulla configurazione del metodo config()
	// Se non ho una session factory (e quindi è null), la inizializzo utilizzando la mia configurazione.
	public Session newSession() {

		if (sessionFactory == null) {
			sessionFactory = config().buildSessionFactory();
		}
		Session session = sessionFactory.openSession();
		return session;
	}

}
