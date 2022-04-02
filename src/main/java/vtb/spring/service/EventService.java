package vtb.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import vtb.spring.dto.EventDto;
import vtb.spring.events.OperaEvent;
import vtb.spring.model.Event;
import vtb.spring.model.Opera;
import vtb.spring.model.Ticket;
//import vtb.spring.repository.JpaEventRepository;
import vtb.spring.repository.JpaEventRepository;
import vtb.spring.repository.JpaOperaRepository;
import vtb.spring.repository.entities.EventEntity;
import vtb.spring.repository.entities.OperaEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements ApplicationContextAware {

    private ApplicationContext ctx;
    private static JpaEventRepository repository;
    private static JpaOperaRepository oRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }


    public EventService(JpaEventRepository repository, JpaOperaRepository oRepository){
        this.repository = repository;
        this.oRepository = oRepository;
    }

    public static List<Event> getAll(){
        List<Event> events = toDomain(repository.findAll());
        return events;
    }

    public static Event getEvent(Integer id){
        return toDomain(repository.getById(id));
    }

    public static List<Event> getAllByEventDate(String date_s) throws ParseException {
        List<Event> events = toDomain(repository.getAllByEventDate(date_s));
        return events;
    }

    public static List<Event> getActualEventList(){
        List<Event> events = toDomain(repository.getAllByStatus("Актуально"));
        return events;
    }

    private static List<Event> toDomain(List<EventEntity> entities){
        return entities.stream()
                .map(EventService::toDomain)
                .collect(Collectors.toList());
    }

    private static Event toDomain(EventEntity entity)  {
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date date = null;
        try {
            date = dt.parse(entity.getEventDate());
        } catch (ParseException e) {
            date = new Date();
        }

        return new Event(toDomain(entity.getOpera()), date, entity.getTotal(), entity.getAvailable());
    }

    private static Opera toDomain(OperaEntity entity){
        return new Opera(entity.getLabel(), entity.getAge(), entity.getType());
    }

    public void saveEvent(Event event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setOpera(oRepository.getById(event.getOpera().getId()));
        eventEntity.setStatus(event.getStatus());
        eventEntity.setAvailable(event.getAvailable());
        eventEntity.setTotal(event.getTotal());
        eventEntity.setEventDate(event.getEventDate().toString());
        repository.save(eventEntity);
    }


    public void delete(Integer id){
        repository.deleteById(id);
    }

//
//    public Ticket buyTicket(Event event, Date date, Integer row, Integer place){
//
//        ctx.publishEvent(
//                new OperaEvent(
//                        new OperaEvent.Info("Покупка билета на : " + event.getOpera().getLabel() +
//                                " дата " + event.getEventDate())
//                )
//        );
//        Ticket ticket = new Ticket(event, 1000, row, place);
//        event.setAvailable(event.getAvailable() - 1);
//        return ticket;
//    }
//
//    public void returnTicket(Ticket ticket){
//        System.out.println("Возврат билета на : " + ticket.getEvent().getOpera().getLabel() + " дата " + ticket.getEvent().getEventDate());
//        ticket.getEvent().setAvailable(ticket.getEvent().getAvailable() + 1);
//    }
//
//    public HashMap<String, Opera> getPrimeOpera(){
//        System.out.println("Получаем список премьер");
//        HashMap<String, Opera> primeOperasMap = new HashMap<>();
//        for(Opera opera : operasMap.values()){
//            if(opera.getType().equals("Премьера"))
//                primeOperasMap.put(opera.getLabel(), opera);
//        }
//        return primeOperasMap;
//    }
//
//
//
//    public String getAnnouncement(String name, Date dt){
//        System.out.println("Получаем анонс мероприятия");
//        Event event = eventsMap.get(name + dt.toString());
//        String announcement = event.getOpera().getType() + " " + name + " состоится " + dt + " на сцена Большого Театра. ";
//
//        ctx.publishEvent(
//                new OperaEvent(
//                        new OperaEvent.Info("Анонс оперы " + name +
//                                "\n" + announcement)
//                )
//        );
//
//        return announcement;
//    }

    public void changeEventDate(Integer id, String dt1, String dt2) throws ParseException {
        System.out.println("Изменение даты мероприятия");
        EventEntity event = repository.getById(id);


       // saveEvent(event.getOpera().getOpera_id(), event.getStatus(), dt2, event.getAvailable(), event.getTotal());
        System.out.println("2");
        String info = "Опера " + event.getOpera().getLabel() + ", запланированная на " + dt1 + ", переносится на " + dt2 +
                ". Приобретенные билеты действительны. Приносим свои извинения за доставленные неудобства.";

        //updateEvent(id, dt2, info);


    }

    public void updateEvent(Integer id, String date, String info) throws ParseException {
        repository.update(id,date,info);
    }


}

