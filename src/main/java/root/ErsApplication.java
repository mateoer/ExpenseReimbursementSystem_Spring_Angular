package root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication /* (exclude = { SecurityAutoConfiguration.class }) */
//@EnableWebMvc
public class ErsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErsApplication.class, args);
	}

}
