package root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@WebServlet(name = "ERS", urlPatterns = { "/EMHome/*", "/login", "/logout", "/json/*",
//"/FMhome/*" })
@SpringBootApplication
public class ErsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErsApplication.class, args);
	}

}
