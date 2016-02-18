package org.chernatkin.garage.simulation;

import java.util.Properties;

import javax.inject.Singleton;
import javax.sql.DataSource;

import org.chernatkin.garage.simulation.service.GarageDao;
import org.chernatkin.garage.simulation.service.GarageService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {

    private static final String DB_URL = "db.url";
    
    private static final String DB_LOGIN = "db.login";
    
    private static final String DB_PASSWORD = "db.pwd";
    
    private static final String DB_POOL_SIZE = "db.pool.size";
    
    private final Properties props;
    
    public ApplicationBinder(Properties props) {
        this.props = props;
    }

    @Override
    protected void configure() {
        bind(GarageDao.class).to(GarageDao.class).in(Singleton.class);
        bind(GarageService.class).to(GarageService.class).in(Singleton.class);
        bindFactory(new DataSourceFactory(props.getProperty(DB_URL), 
                                          props.getProperty(DB_LOGIN),
                                          props.getProperty(DB_PASSWORD),
                                          Integer.parseInt(props.getProperty(DB_POOL_SIZE))))
                   .to(DataSource.class)
                   .in(Singleton.class);
    }
}
