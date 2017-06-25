package com.balazsholczer.launcher;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.balazsholczer"})
@EnableJpaRepositories({"com.balazsholczer"})
@EntityScan({"com.balazsholczer"})
public class SpringBootAppApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootAppApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}
	
//	@Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
//        lef.setDataSource(dataSource);
//        lef.setJpaVendorAdapter(jpaVendorAdapter);
//        lef.setPackagesToScan("com.balazsholczer");
//        return lef;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setShowSql(false);
//        hibernateJpaVendorAdapter.setGenerateDdl(true);
//        hibernateJpaVendorAdapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
//        return hibernateJpaVendorAdapter;
//    }
}