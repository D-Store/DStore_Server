package gg.jominsubyungsin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class JominsubyungsinApplication {

  public static void main(String[] args) {
    SpringApplication.run(JominsubyungsinApplication.class, args);
  }

}
