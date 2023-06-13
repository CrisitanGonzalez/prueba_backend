package cl.prueba.mantenedor_backend.config;


import cl.prueba.mantenedor_backend.mapper.DataMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@SpringBootApplication
@ComponentScan(
        basePackages = {
                "cl.prueba.mantenedor_backend.controller",
                "cl.prueba.mantenedor_backend.services",
                "cl.prueba.mantenedor_backend.repository"
        })
@Log4j2
public class GlobalConfiguration implements WebMvcConfigurer {
    @Autowired
    private Environment environment;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
        bundle.setDefaultEncoding("UTF-8");
        Properties fileEncodings = new Properties();
        fileEncodings.setProperty("application-messages", "UTF-8");
        bundle.setFileEncodings(fileEncodings);
        bundle.setFallbackToSystemLocale(true);
        bundle.setBasename("classpath:application-messages");
        return bundle;
    }


    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeHandlersPackage("cl.prueba.mantenedor_backend.typehandlers");
        Resource[] arrResource = new PathMatchingResourcePatternResolver()
                .getResources("postgresql/*.xml");
        sessionFactory.setMapperLocations(arrResource);
        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("HikariPool Login");
        config.setJdbcUrl(environment.getProperty("cl.prueba.datasource.url"));
        config.setUsername(environment.getProperty("cl.prueba.datasource.username"));
        config.setPassword(environment.getProperty("cl.prueba.datasource.password"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(20);
        return new HikariDataSource(config);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "DataBaseMapperFactory")
    public MapperFactoryBean<DataMapper> mapperFactory() throws Exception {
        MapperFactoryBean<DataMapper> mapperFactory = new MapperFactoryBean<>();
        mapperFactory.setMapperInterface(DataMapper.class);
        mapperFactory.setSqlSessionFactory(sqlSessionFactory());
        return mapperFactory;
    }



}
