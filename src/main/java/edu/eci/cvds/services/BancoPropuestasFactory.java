package edu.eci.cvds.services;

import com.google.inject.Injector;

import edu.eci.cvds.managedbeans.ShiroBean;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisIniciativaDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUsuarioDAO;
import edu.eci.cvds.services.impl.BancoPropuestasImpl;

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
                bind(BancoPropuestas.class).to(BancoPropuestasImpl.class);
                

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


    public BancoPropuestas getBancoPropuestasTest(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
        }

        return optInjector.get().getInstance(BancoPropuestas.class);
    }
    
    public BancoPropuestas getBancoPropuestas(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
        }

        return optInjector.get().getInstance(BancoPropuestas.class);
    }


    public static BancoPropuestasFactory getInstance(){
        return instance;
    }

}