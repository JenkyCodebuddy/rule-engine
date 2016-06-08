package jenky.codebuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@ImportResource("spring.xml")
public class StartApplication { //class for starting the spring application

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
}
