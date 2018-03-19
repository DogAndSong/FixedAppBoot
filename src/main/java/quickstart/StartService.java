package quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/*

扫描的是startService的一下的包

 */
@SpringBootApplication
//@EnableScheduling
@ComponentScan(basePackages = {"C"})
public class StartService extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(StartService.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StartService.class);
    }
}
