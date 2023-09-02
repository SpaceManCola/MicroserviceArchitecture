package inc.cognito.MicroserviceArchitecture.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean("hikariConfig")
    public HikariConfig hikariConfig(Environment env) {
        HikariConfig config = new HikariConfig();
        config.setPoolName("hikariPostgresPool");
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(env.getRequiredProperty("db.url"));
        config.setUsername(env.getRequiredProperty("db.username"));
        config.setPassword(env.getRequiredProperty("db.password"));
        config.setMaximumPoolSize(env.getProperty("db.maximumPoolSize", Integer.class, 10));
        config.setMinimumIdle(env.getProperty("db.minimumIdle", Integer.class, 10));
        config.setMaxLifetime(env.getProperty("db.maxLifeTime", Integer.class, 20_000));
        config.setConnectionTimeout(env.getProperty("db.connectionTimeout", Integer.class, 5_000));
        config.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        config.setAutoCommit(false);
        config.setReadOnly(false);
        return config;
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(Environment env, DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        txManager.setDefaultTimeout(env.getProperty("db.transaction.timeout", Integer.class, 10));
        txManager.setGlobalRollbackOnParticipationFailure(false);
        return txManager;
    }

}
