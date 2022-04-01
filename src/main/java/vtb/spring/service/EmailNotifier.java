package vtb.spring.service;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import vtb.spring.events.OperaEvent;

@Component
public class EmailNotifier implements ApplicationListener<OperaEvent> {


    @Override
    public void onApplicationEvent(OperaEvent event) {
        final OperaEvent.Info info = (OperaEvent.Info) event.getSource();
        System.out.println("--------------------------\nОтправка на почту сообщения: \n"
                + info.getTicketInfo() + "\n------------------------");

    }
}
