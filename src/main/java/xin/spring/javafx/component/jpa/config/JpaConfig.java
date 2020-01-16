package xin.spring.javafx.component.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import xin.spring.javafx.domain.DataBase;
import xin.spring.javafx.session.ApplicationSession;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * jpa配置类
 */
@Configuration
@ComponentScan( basePackages = {"xin.spring.javafx.component"})
@EnableJpaRepositories( basePackages = {"xin.spring.javafx.repositories"})
public class JpaConfig {

    // 名字必须是entityManagerFactory,或者把@bean中name属性设置为entityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        // 设置数据库(如果在hibernate中配置了连接池,则不需要设置)
        // em.setDataSource(dataSource());
        // 指定Entity所在的包
        em.setPackagesToScan("xin.spring.javafx.domain");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        // 配置属性
        Properties properties = new Properties();
        // 会话中获取连接数据源
        DataBase dataBase = ApplicationSession.getInstance().get(DataBase.class);

        properties.setProperty("hibernate.connection.driver_class", dataBase.getDataBaseVersion().getDriver());
        properties.setProperty("hibernate.connection.url", dataBase.getDataBaseVersion().getUrl() + dataBase.getDatabases());
        properties.setProperty("hibernate.connection.username", dataBase.getUsername());
        properties.setProperty("hibernate.connection.password", dataBase.getPassword());
        properties.setProperty("hibernate.dialect", dataBase.getDataBaseVersion().getDialect());
        properties.setProperty("hibernate.connection.provider_class", dataBase.getProviderClass());
        properties.setProperty("hibernate.c3p0.min_size", dataBase.getMinSize());
        properties.setProperty("hibernate.c3p0.max_size", dataBase.getMaxSize());
        properties.setProperty("hibernate.hbm2ddl.auto", dataBase.getHbm2ddl());
        properties.setProperty("hibernate.show_sql", dataBase.getShowSql());
        properties.setProperty("format_sql", dataBase.getFormatSql());
        em.setJpaProperties(properties);
        return em;
    }

    // 名字必须是transactionManager,或者把@bean中name属性设置为transactionManager
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

}
