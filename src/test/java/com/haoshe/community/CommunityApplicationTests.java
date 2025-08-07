package com.haoshe.community;

import com.haoshe.community.config.AlphaConfig;
import com.haoshe.community.dao.AlphaDao;
import com.haoshe.community.service.AlphaService;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
// It tells Spring which configuration classes or XML files to use to load the application context for the test.
@ContextConfiguration(classes = CommunityApplication.class)
// Implement this interface and call setApplicationContext can help us get the IoC container.
class CommunityApplicationTests implements ApplicationContextAware {

	// create a variable to hold the IoC container
	private ApplicationContext applicationContext;
	@Override
	// Spring automatically calls this method to inject the IoC container
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	// test the IoC container
	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class); // get bean by its type
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class); // get bean by name and type
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	/*
	AlphaDao has multiple beans in the Spring context, AlphaMyBatisImpl and AlphaHibernateImpl
	I want to specify which implementation to inject into my field
	 */
	@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){ // Dependency Injection
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}
