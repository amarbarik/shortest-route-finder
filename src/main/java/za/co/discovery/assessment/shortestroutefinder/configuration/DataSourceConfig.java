package za.co.discovery.assessment.shortestroutefinder.configuration;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class DataSourceConfig {

    private Logger logger = Logger.getLogger(DataSourceConfig.class.getName());

    @Bean
    public DataSource dataSource() {
        EmbeddedDataSource dataSource = new EmbeddedDataSource();
        dataSource.setConnectionAttributes("create=true");
        dataSource.setCreateDatabase("InterstellarDB");
        dataSource.setDatabaseName("InterstellarDB");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to connect to the database: " + e);
        }

        return dataSource;
    }

}
