package pt.gmartins.employee.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class SpringBootAppApplication {

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(512000000L));
		factory.setMaxRequestSize(DataSize.ofBytes(512000000L));
		return factory.createMultipartConfig();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

}