package com.perennial.doctorappointmentbooking.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mySqlEntityManagerFactory",
        transactionManagerRef = "mySqlTransactionManager" ,
        basePackages = "com.perennial.doctorappointmentbooking.repo"
)
@Profile("dev")
    public class DbOneConfig{
        @Primary
        @Bean
        @ConfigurationProperties(prefix = "db1.datasource")
        public DataSource mySqlDataSource(){
            return DataSourceBuilder.create().build();
        }
        @Primary
        @Bean
        public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
                EntityManagerFactoryBuilder entityManagerFactoryBuilder
        ){
            HashMap<String,Object> properties=new HashMap<>();
            properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
            properties.put("hibernate.hbm2ddl.auto","update");
            return entityManagerFactoryBuilder.dataSource(mySqlDataSource())
                    .packages("com.perennial.doctorappointmentbooking.entity")
                    .properties(properties).build();
        }
        @Primary
        @Bean
        public PlatformTransactionManager mySqlTransactionManager(
                @Qualifier("mySqlEntityManagerFactory")
                        EntityManagerFactory entityManagerFactory
        )
        {
            return new JpaTransactionManager(entityManagerFactory);
        }
    }

