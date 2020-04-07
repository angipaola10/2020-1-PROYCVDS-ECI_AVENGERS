package edu.eci.cvds.services;

import static com.google.inject.Guice.createInjector;

import java.util.Optional;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Injector;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUsuarioDAO;


public class BancoPropuestasFactory {

	private static BancoPropuestasFactory instance = new BancoPropuestasFactory();

	
	private BancoPropuestasFactory() {
	}

	private Injector myBatisInjector(String env, String pathResource, JdbcHelper jdbcHelper) {
		return createInjector(new XMLMyBatisModule() {
			@Override
			protected void initialize() {
				setEnvironmentId(env);
				install(jdbcHelper);
				setClassPathResource(pathResource);
				bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
			}
		});
	}

	public BancoPropuestasServices getBlogServices() {
		if (!optInjector.isPresent()) {
			optInjector = Optional.of(myBatisInjector("development", "mybatis-config.xml", JdbcHelper.MySQL));
		}

		return optInjector.get().getInstance(BancoPropuestasServices.class);
	}

	public static BancoPropuestasFactory getInstance() {
		return instance;
	}

}
