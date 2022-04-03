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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperaService implements ApplicationContextAware {

    private ApplicationContext ctx;

    private static JpaOperaRepository repository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public OperaService(JpaOperaRepository repository){
        this.repository = repository;
    }

    public static List<Opera> getAll(){
        List<Opera> operas = toDomain(repository.findAll());
        return operas;
    }

    public static List<Opera>  getAllLike(String pattern){
        List<Opera> operas = toDomain(repository.findAllByLabelLike("%" + pattern + "%"));
        return operas;
    }

    public static Opera getOpera(Integer id){
        return toDomain(repository.getById(id));
    }

    private static List<Opera> toDomain(List<OperaEntity> entities){
        return entities.stream()
                .map(OperaService::toDomain)
                .collect(Collectors.toList());
    }

    private static Opera toDomain(OperaEntity entity){
        return new Opera(entity.getLabel(), entity.getAge(), entity.getType());
    }

    public void save(Opera opera){
        OperaEntity operaEntity = new OperaEntity();
        operaEntity.setLabel(opera.getLabel());
        operaEntity.setAge(opera.getAge());
        operaEntity.setType(opera.getType());
        repository.save(operaEntity);
    }

    public void update(Opera opera){
        OperaEntity byId = repository.getById(opera.getId());

        OperaEntity operaEntity = new OperaEntity();
        operaEntity.setLabel(opera.getLabel());
        operaEntity.setAge(opera.getAge());
        operaEntity.setType(opera.getType());
        repository.save(operaEntity);
    }

    public void delete(Integer operaId){
        repository.deleteById(operaId);
    }

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
//            primeOperasMap.put(opera.getLabel(), opera);
//        }
//        return primeOperasMap;
//    }
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


}
