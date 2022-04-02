package vtb.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtb.spring.dto.EventDto;
import vtb.spring.dto.OperaDto;
import vtb.spring.mappers.EventMapper;
import vtb.spring.model.Event;
import vtb.spring.service.EventService;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;
    private EventMapper eventMapper;

    @Autowired
    public EventController(EventService eventService, EventMapper eventMapper){
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping
    public Collection<EventDto> getAllEvents(){
        List<Event> events = EventService.getAll();
        return events.stream().map(eventMapper::toDto).collect(toList());
    }

    @GetMapping("/event/{id}")
    public EventDto getEvent(@PathVariable("id") Integer id){
        return eventMapper.toDto(eventService.getEvent(id));

    }

    @GetMapping("/date/{date}")
    public Collection<EventDto> getEventsByDate(@PathVariable("date") String date) throws ParseException {
        List<Event> events = EventService.getAllByEventDate(date);
        return events.stream().map(eventMapper::toDto).collect(toList());
    }

    @GetMapping("/actual")
    public Collection<EventDto> getActualEvents(){
        List<Event> events = EventService.getActualEventList();
        return events.stream().map(eventMapper::toDto).collect(toList());
    }

    @PostMapping
    public String create(@RequestBody EventDto eventDto){
        eventService.saveEvent(eventMapper.toDomain(eventDto));
        return "redirect:/events";
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody EventDto eventDto){
        //eventService.update(eventMapper(eventDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        eventService.delete(id);
    }

}