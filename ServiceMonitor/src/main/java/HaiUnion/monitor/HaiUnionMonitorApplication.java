package HaiUnion.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer
@SpringBootApplication
public class HaiUnionMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaiUnionMonitorApplication.class, args);
	}
}