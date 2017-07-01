package onlab.rest.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@ComponentScan
public class Application extends SpringBootServletInitializer {

	//ApplicationContext context = 
            //new ClassPathXmlApplicationContext("Beans.xml");
	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<Application> applicationClass = Application.class;
}
