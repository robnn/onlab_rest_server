package onlab.rest.spring;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableAutoConfiguration
public class Config {
	@Bean
    DataSource dataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser("onlab");
        dataSource.setPassword("onlab");
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:XE");
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }	
	
}
