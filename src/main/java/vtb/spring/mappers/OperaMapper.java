package vtb.spring.mappers;

import org.mapstruct.Mapper;
import vtb.spring.dto.OperaDto;
import vtb.spring.model.Opera;

@Mapper(componentModel = "spring")
public interface OperaMapper {

    OperaDto toDto(Opera opera);
    Opera toDomain(OperaDto operaDto);

}


