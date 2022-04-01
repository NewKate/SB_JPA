package vtb.spring.repository;

import vtb.spring.repository.entities.OperaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface JpaOperaRepository extends JpaRepository<OperaEntity, Integer>{

    void deleteById(Integer id);

    void deleteByLabel(String label);

    List<OperaEntity> findAllByLabelLike(String pattern);


}
