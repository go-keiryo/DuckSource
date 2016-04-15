package edu.stevens.ssw690.DuckSource.test;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import edu.stevens.ssw690.DuckSource.controller.IndexController;
import edu.stevens.ssw690.DuckSource.controller.OpportunityController;
import edu.stevens.ssw690.DuckSource.controller.RegisterReviewController;
import edu.stevens.ssw690.DuckSource.controller.SubmitController;
import edu.stevens.ssw690.DuckSource.dao.DuckUserDao;
import edu.stevens.ssw690.DuckSource.dao.DuckUserDaoImpl;
import edu.stevens.ssw690.DuckSource.dao.OpportunityDao;
import edu.stevens.ssw690.DuckSource.dao.OpportunityDaoImpl;
import edu.stevens.ssw690.DuckSource.dao.OpportunityRegisteredDao;
import edu.stevens.ssw690.DuckSource.dao.OpportunityRegisteredDaoImpl;
import edu.stevens.ssw690.DuckSource.dao.OpportunityReviewIssueDao;
import edu.stevens.ssw690.DuckSource.dao.OpportunityReviewIssueDaoImpl;
import edu.stevens.ssw690.DuckSource.dao.OpportunitySubmittedDao;
import edu.stevens.ssw690.DuckSource.dao.OpportunitySubmittedDaoImpl;
import edu.stevens.ssw690.DuckSource.dao.OpportunityTimeDao;
import edu.stevens.ssw690.DuckSource.dao.OpportunityTimeDaoImpl;
import edu.stevens.ssw690.DuckSource.dao.ReviewIssueDao;
import edu.stevens.ssw690.DuckSource.dao.ReviewIssueDaoImpl;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.DuckUserManagerImpl;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManagerImpl;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManagerImpl;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManagerImpl;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManagerImpl;
import edu.stevens.ssw690.DuckSource.service.OpportunityTimeManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityTimeManagerImpl;
import edu.stevens.ssw690.DuckSource.service.ReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.ReviewIssueManagerImpl;

@Configuration
@ComponentScan(basePackages = {"edu.stevens.ssw690.DuckSource"})
@EnableWebMvc
public class TestConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public ViewResolver configureViewResolver() {
	     InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
	     viewResolve.setPrefix("/WEB-INF/");
	     viewResolve.setSuffix(".jsp");

	     return viewResolve;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	@Bean(name = "dataSource")
    public DataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/duckdb");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
 
        /*
	        HSQLDB
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
	        dataSource.setUrl("jdbc:hsqldb:mem:DuckSource");
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");
        */
        
        return dataSource;        
    }
	
	 @Bean(name = "entityManagerFactory")
	  public EntityManagerFactory entityManagerFactory()
	  {
	    LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
	    lcemfb.setDataSource(dataSource());
	    lcemfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lcemfb.setPackagesToScan(new String[] { "edu.stevens.ssw690.DuckSource.model" });
	    lcemfb.setJpaProperties(hibProperties());
	    lcemfb.afterPropertiesSet();
	    return lcemfb.getObject();
	  }

	 private Properties hibProperties() {
         Properties properties = new Properties();
         properties.put("hibernate.archive.autodetection", "class,hbm");
         properties.put("hibernate.hbm2ddl.auto, env.getRequired","create");
         properties.put("hibernate.show_sql","true");
         properties.put("hibernate.dialect","org.hibernate.dialect.HSQLDialect");
         return properties;        
	}
	 
	 @Bean(name = "transactionManager")
	   public PlatformTransactionManager annotationDrivenTransactionManager()
	   {
	     JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
	     jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
	     return jpaTransactionManager;
	   } 
	 
	@Bean
    public IndexController indexController() {
        return new IndexController();
    }
	
	@Bean
    public OpportunityController opportunityController() {
        return new OpportunityController();
    }
	
	@Bean
    public RegisterReviewController registerReviewController() {
        return new RegisterReviewController();
    }
	
	@Bean
    public SubmitController submitController() {
        return new SubmitController();
    }
	
	@Bean
    public DuckUserManager duckUserService() {
        return new DuckUserManagerImpl();
    }
	
	@Bean
    public OpportunityManager opportunityService() {
        return new OpportunityManagerImpl();
    }
	
	@Bean
    public OpportunityRegisteredManager opportunityRegisteredService() {
        return new OpportunityRegisteredManagerImpl();
    }
	
	@Bean
    public OpportunityReviewIssueManager opportunityReviewIssueService() {
        return new OpportunityReviewIssueManagerImpl();
    }
	
	@Bean
    public OpportunitySubmittedManager opportunitySubmittedService() {
        return new OpportunitySubmittedManagerImpl();
    }
	
	@Bean
    public OpportunityTimeManager opportunityTimeService() {
        return new OpportunityTimeManagerImpl();
    }
	
	@Bean
    public ReviewIssueManager reviewIssueService() {
        return new ReviewIssueManagerImpl();
    }
	
	
	@Bean
    public DuckUserDao duckUserDao() {
        return new DuckUserDaoImpl();
    }
	
	@Bean
    public OpportunityDao opportunityDao() {
        return new OpportunityDaoImpl();
    }
	
	@Bean
    public OpportunityRegisteredDao opportunityRegisteredDao() {
        return new OpportunityRegisteredDaoImpl();
    }
	
	@Bean
    public OpportunityReviewIssueDao opportunityReviewIssueDao() {
        return new OpportunityReviewIssueDaoImpl();
    }
	
	@Bean
    public OpportunitySubmittedDao opportunitySubmittedDao() {
        return new OpportunitySubmittedDaoImpl();
    }
	
	@Bean
    public OpportunityTimeDao opportunityTimeDao() {
        return new OpportunityTimeDaoImpl();
    }
	
	@Bean
    public ReviewIssueDao reviewIssueDao() {
        return new ReviewIssueDaoImpl();
    }
}
