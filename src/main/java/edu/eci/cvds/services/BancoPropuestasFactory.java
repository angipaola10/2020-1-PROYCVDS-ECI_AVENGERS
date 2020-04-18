package edu.eci.cvds.services;

import com.google.inject.Injector;

import edu.eci.cvds.managedbeans.ShiroBean;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisIniciativaDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUsuarioDAO;

import org.mybatis.guice.XMLMyBatisModule;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;

public class BancoPropuestasFactory {

    private static BancoPropuestasFactory instance = new BancoPropuestasFactory();

    private static Optional<Injector> optInjector;

    private Injector myBatisInjector(String env, String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId(env);
                setClassPathResource(pathResource);
                bind(IniciativaDAO.class).to(MyBatisIniciativaDAO.class);
                bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);

            }
        });
    }

    private  BancoPropuestasFactory(){
        optInjector = Optional.empty();
    }

    public ShiroBean detShiroBean(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
        }

        return optInjector.get().getInstance(ShiroBean.class);
    }


    public ShiroBean getBancoPropuestasTest(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test","mybatis-config-h2.xml"));
        }

        return optInjector.get().getInstance(ShiroBean.class);
    }


    public static BancoPropuestasFactory getInstance(){
        return instance;
    }

}