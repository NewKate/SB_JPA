package vtb.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.ViewResolver;

import java.text.ParseException;


@SpringBootApplication
@EntityScan({"vtb.spring.repository.entities"})
public class OperaSalesApplication {

    public static void main(String[] args) throws ParseException {


        final ConfigurableApplicationContext ctx = SpringApplication.run(OperaSalesApplication.class, args);

        //System.out.println(ctx.getBean(ViewResolver.class));

//        final OperaService operaService = ctx.getBean(OperaService.class);

 //       operaService.printAll();


    }

}
