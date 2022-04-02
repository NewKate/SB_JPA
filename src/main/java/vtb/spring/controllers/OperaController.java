package vtb.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtb.spring.dto.EventDto;
import vtb.spring.dto.OperaDto;
import vtb.spring.mappers.EventMapper;
import vtb.spring.mappers.OperaMapper;
import vtb.spring.model.Opera;
import vtb.spring.service.OperaService;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/operas")
public class OperaController {

    private OperaService operaService;
    private OperaMapper operaMapper;
    private EventMapper eventMapper;

    @Autowired
    public OperaController(OperaService operaService, OperaMapper operaMapper, EventMapper eventMapper){
        this.operaService = operaService;
        this.operaMapper = operaMapper;
        this.eventMapper = eventMapper;
    }

    @GetMapping
    @ResponseBody
    public Collection<OperaDto> getAllOpers(){
        List<Opera> operas = OperaService.getAll();
        return operas.stream().map(operaMapper::toDto).collect(toList());
    }

    @GetMapping("/like/{pattern}")
    public Collection<OperaDto> getOpersLike(@PathVariable("pattern") String pattern){
        List<Opera> operas = OperaService.getAllLike(pattern);
        return operas.stream().map(operaMapper::toDto).collect(toList());
    }

    @GetMapping("/{id}")
    public OperaDto getOpera(@PathVariable("id") Integer id){
        return operaMapper.toDto(OperaService.getOpera(id));

    }

    @PostMapping
    public String create(@RequestBody OperaDto operaDto){
        operaService.save(operaMapper.toDomain(operaDto));
        return "redirect:/operas";
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody OperaDto operaDto){
        //operaService.save(operaMapper.toDomain(operaDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        operaService.delete(id);
    }
}
