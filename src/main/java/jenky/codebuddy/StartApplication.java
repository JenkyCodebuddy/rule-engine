package jenky.codebuddy;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
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
//		Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
//			private Gson gson = new Gson();
//
//			public <T> T readValue(String value, Class<T> valueType) {
//				return gson.fromJson(value, valueType);
//			}
//
//			public String writeValue(Object value) {
//				return gson.toJson(value);
//			}
//		});
		SpringApplication.run(StartApplication.class, args);
	}
}
