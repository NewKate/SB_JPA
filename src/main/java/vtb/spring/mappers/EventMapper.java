package vtb.spring.mappers;

import org.mapstruct.Mapper;
import vtb.spring.dto.EventDto;
import vtb.spring.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(Event event);
    Event toDomain(EventDto eventDto);

}