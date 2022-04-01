package vtb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vtb.spring.repository.entities.EventEntity;

import java.util.Date;
import java.util.List;


public interface JpaEventRepository extends JpaRepository<EventEntity, Integer> {

    void deleteById(Integer id);

    List<EventEntity> getAllByEventDate(String date);

    List<EventEntity> getAllByStatus(String status);

    @Transactional
    @Modifying
    @Query("update event u set u.status = 'Отмена', u.eventDate = :newDate, u.comment = :comment where u.event_id = :id ")
    void update(@Param("id") Integer id, @Param("newDate") String newDate, @Param("comment") String comment);

}
