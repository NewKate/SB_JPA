package vtb.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import vtb.spring.model.Event;
import vtb.spring.model.Opera;
import vtb.spring.model.Ticket;
import vtb.spring.repository.entities.OperaEntity;
import vtb.spring.service.OperaService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@SpringBootApplication
@EntityScan({"vtb.spring.repository.entities"})
public class OperaSalesApplication {

    public static void main(String[] args) throws ParseException {


        final ConfigurableApplicationContext ctx = SpringApplication.run(OperaSalesApplication.class, args);

        final OperaService operaService = ctx.getBean(OperaService.class);

        operaService.save("Кармен", 12, "Постоянный репертуар");
        operaService.save("Валькирия", 16, "Премьера");
        operaService.save("Борис Годунов", 16, "Постоянный репертуар");
        operaService.save("Травиата", 12, "Премьера");
        operaService.save("Князь Игорь", 12, "Премьера");

        operaService.printAll();
        operaService.delete(5);
        System.out.println("----------------");
        operaService.printAll();
        operaService.printAllLike("К%");


        operaService.saveEvent(1, "Актуально", "11.11.2021 19:00", 500, 500);
        operaService.saveEvent(1, "Актуально", "12.11.2021 19:00", 500, 500);
        operaService.saveEvent(1, "Актуально", "13.11.2021 19:00", 500, 500);
        operaService.saveEvent(1, "Актуально", "14.11.2021 19:00", 500, 500);
        operaService.saveEvent(2, "Актуально", "15.11.2021 13:00", 500, 500);
        operaService.saveEvent(2, "Актуально", "15.11.2021 19:00", 500, 500);

        operaService.printAllEvents();
        operaService.getAllByEventDate("15.11.2021 19:00");
        operaService.getActualEventList();


        //operaService.printAll();

//        operaService.changeEventDate(27, "15.11.2021 19:00", "15.12.2021 19:00");
//        operaService.printAllEvents();

    }

}
