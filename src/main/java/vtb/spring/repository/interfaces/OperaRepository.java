package vtb.spring.repository.interfaces;

import vtb.spring.repository.entities.OperaEntity;

import java.util.Collection;

public interface OperaRepository {

    Collection<OperaEntity> getAll();
    boolean save(OperaEntity opera);
    boolean delete(int operaId);
}
