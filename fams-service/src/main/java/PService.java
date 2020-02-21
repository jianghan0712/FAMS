
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.core.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams" })
//@ImportResource(locations="classpath:config/${ServiceName}/${Env}/${Instance}/Ignite.xml")
public class PService {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PService.class);
		application.run(args);
	}
}
