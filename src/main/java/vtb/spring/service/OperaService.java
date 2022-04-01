package vtb.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
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
import java.util.Date;
import java.util.HashMap;

@Service
public class OperaService implements ApplicationContextAware {

    private ApplicationContext ctx;

    private HashMap<String, Opera> operasMap = new HashMap<>();
    private HashMap<String, Event> eventsMap = new HashMap<>();

    private JpaOperaRepository repository;
    private JpaEventRepository eRepository;

    public OperaService(JpaOperaRepository repository, JpaEventRepository eRepository){
        this.repository = repository;
        this.eRepository = eRepository;
    }

    //**************db OPERA **********************
    public void printAll(){
        repository.findAll().forEach(ent -> {
            System.out.println(ent.getLabel() + " - " + ent.getOpera_id());
        });
    }

    public void printAllLike(String pattern){
        for (OperaEntity opera : repository.findAllByLabelLike(pattern)){
            System.out.println(opera.getLabel());
        };
    }

    public void save(String label, Integer age, String type){
        OperaEntity  opera = new OperaEntity();
        opera.setLabel(label);
        opera.setAge(age);
        opera.setType(type);
        repository.save(opera);
    }

    public void delete(Integer operaId){
        repository.deleteById(operaId);
    }

    public void delete2(String label){
        repository.deleteByLabel(label);
    }

    //**************db EVENT**********************

    public void saveEvent(Integer opera_id, String status, String date_s, Integer available, Integer total) {
        EventEntity event = new EventEntity();
        event.setOpera(repository.getById(opera_id));
        event.setStatus(status);
        event.setAvailable(available);
        event.setTotal(total);
        event.setEventDate(date_s);
        eRepository.save(event);
    }

    public void printAllEvents(){
        System.out.println("Получаем список всех мероприятий");
        eRepository.findAll().forEach(ent -> {
            System.out.println(ent.getOpera().getLabel() + " состоится " + ent.getEventDate() + " статус : " + ent.getStatus());
        });
    }

    public void getAllByEventDate(String date_s) throws ParseException {
        System.out.println("Получаем список мероприятий на дату " + date_s);
        /*SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date date = dt.parse(date_s);*/

        for (EventEntity event : eRepository.getAllByEventDate(date_s)){
            System.out.println(event.getOpera().getLabel() + " состоится " + event.getEventDate());
        };
    }


    public void getActualEventList(){
        System.out.println("Получаем список всех актуальных мероприятий");
        for (EventEntity event : eRepository.getAllByStatus("Актуально")){
            System.out.println(event.getOpera().getLabel() + " состоится " + event.getEventDate());
        };
    }


    public Ticket buyTicket(Event event, Date date, Integer row, Integer place){

        ctx.publishEvent(
                new OperaEvent(
                        new OperaEvent.Info("Покупка билета на : " + event.getOpera().getLabel() +
                                " дата " + event.getEventDate())
                )
        );
        Ticket ticket = new Ticket(event, 1000, row, place);
        event.setAvailable(event.getAvailable() - 1);
        return ticket;
    }

    public void returnTicket(Ticket ticket){
        System.out.println("Возврат билета на : " + ticket.getEvent().getOpera().getLabel() + " дата " + ticket.getEvent().getEventDate());
        ticket.getEvent().setAvailable(ticket.getEvent().getAvailable() + 1);
    }

    public HashMap<String, Opera> getPrimeOpera(){
        System.out.println("Получаем список премьер");
        HashMap<String, Opera> primeOperasMap = new HashMap<>();
        for(Opera opera : operasMap.values()){
            if(opera.getType().equals("Премьера"))
            primeOperasMap.put(opera.getLabel(), opera);
        }
        return primeOperasMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public String getAnnouncement(String name, Date dt){
        System.out.println("Получаем анонс мероприятия");
        Event event = eventsMap.get(name + dt.toString());
        String announcement = event.getOpera().getType() + " " + name + " состоится " + dt + " на сцена Большого Театра. ";

        ctx.publishEvent(
                new OperaEvent(
                        new OperaEvent.Info("Анонс оперы " + name +
                                "\n" + announcement)
                )
        );

        return announcement;
    }

    public void changeEventDate(Integer id, String dt1, String dt2) throws ParseException {
        System.out.println("Изменение даты мероприятия");
        EventEntity event = eRepository.getById(id);
        System.out.println(event);
        System.out.println(event.getStatus());
        System.out.println(event.getOpera().getOpera_id());
        saveEvent(event.getOpera().getOpera_id(), event.getStatus(), dt2, event.getAvailable(), event.getTotal());
        System.out.println("2");
        String info = "Опера " + event.getOpera().getLabel() + ", запланированная на " + dt1 + ", переносится на " + dt2 +
                ". Приобретенные билеты действительны. Приносим свои извинения за доставленные неудобства.";

        updateEvent(id, dt2, info);


    }

    public void updateEvent(Integer id, String date, String info) throws ParseException {
        eRepository.update(id,date,info);
    }


}
