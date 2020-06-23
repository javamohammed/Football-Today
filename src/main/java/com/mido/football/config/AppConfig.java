package com.mido.football.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages  = {"com.mido.football.dao.repository"}, entityManagerFactoryRef = "sessionFactory", transactionManagerRef = "transactionManagerJpa")
@ComponentScan(basePackages ="com.mido.football")
@EnableTransactionManagement
@PropertySource({"classpath:persistence-mysql.properties","classpath:routes.properties"})
public class AppConfig  implements WebMvcConfigurer{

	   private Logger logger = Logger.getLogger(getClass().getName());
	   
	   @Autowired
	   private ApplicationContext applicationContext;
	   
	   @Autowired
		private Environment env;

	   
	   @Bean
		public DataSource myDataSource() {
			
			// create connection pool
			ComboPooledDataSource myDataSource = new ComboPooledDataSource();

			
			// set the jdbc driver
			try {
				myDataSource.setDriverClass(env.getProperty("jdbc.driver"));		
			}
			catch (PropertyVetoException exc) {
				
				throw new RuntimeException(exc);
			}
			
			// for sanity's sake, let's log url and user ... just to make sure we are reading the data
			logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
			logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
			
			// set database connection props
			myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			myDataSource.setUser(env.getProperty("jdbc.user"));
			myDataSource.setPassword(env.getProperty("jdbc.password"));
			
			// set connection pool props
			myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
			myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
			myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
			myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

			return myDataSource;
		}
	   
	   @Bean
		public LocalSessionFactoryBean sessionFactory(){
			
			// create session factorys
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			
			// set the properties
			sessionFactory.setDataSource(myDataSource());
			sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
			sessionFactory.setHibernateProperties(getHibernateProperties());
			
			return sessionFactory;
		}
	   
	   private Properties getHibernateProperties() {

			// set hibernate properties
			Properties props = new Properties();

			props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
			props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			
			return props;				
		}
	   
	   	@Bean
		@Autowired
		//@Qualifier("entityManagerFactory")
		public HibernateTransactionManager transactionManagerHibernate(SessionFactory sessionFactory) {
			
			// setup transaction manager based on session factory
			HibernateTransactionManager txManager = new HibernateTransactionManager();
			txManager.setSessionFactory(sessionFactory);
	
			return txManager;
		}	
	   
		@Bean
		@Autowired
		public JpaTransactionManager transactionManagerJpa(EntityManagerFactory sessionFactory) {
			
			// setup transaction manager based on session factory
	   		JpaTransactionManager txManager = new JpaTransactionManager();
			txManager.setEntityManagerFactory(sessionFactory);;
	
			return txManager;
		}
/*
		@Bean
		//@Qualifier("sessionFactory")
	    public LocalEntityManagerFactoryBean entityManagerM() {
	        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
	        factoryBean.setPersistenceUnitName(env.getProperty("jdbc.db"));
	          
	        return factoryBean;
	    }
	   	*/
	   private int getIntProperty(String propName) {
			
			String propVal = env.getProperty(propName);
			
			// now convert to int
			int intPropVal = Integer.parseInt(propVal);
			
			return intPropVal;
		}	

	   /*
	    * STEP 1 - Create SpringResourceTemplateResolver
	    * */
	   @Bean
	   public SpringResourceTemplateResolver templateResolver() {
	      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	      templateResolver.setApplicationContext(applicationContext);
	      templateResolver.setPrefix("/WEB-INF/views/");
	      templateResolver.setSuffix(".html");
	      return templateResolver;
	   }
	   
	   /*
	    * STEP 2 - Create SpringTemplateEngine
	    * */
	   @Bean
	   public SpringTemplateEngine templateEngine() {
	      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	      templateEngine.setTemplateResolver(templateResolver());
	      templateEngine.setEnableSpringELCompiler(true);
	      return templateEngine;
	   }
	   
	   /*
	    * STEP 3 - Register ThymeleafViewResolver
	    * */
	   @Override
	   public void configureViewResolvers(ViewResolverRegistry registry) {
	      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	      resolver.setTemplateEngine(templateEngine());
	      registry.viewResolver(resolver);
	   }
	
	
	/*
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setPrefix("/WEB-INF/views/");
		viewResolve.setSuffix(".jsp");
		
		return viewResolve;
	}*/
	
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	  
	  registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
      registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/"); 
      registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/"); 
      registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/"); 
      registry.addResourceHandler("/scss/**").addResourceLocations("classpath:/static/scss/"); 
      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
