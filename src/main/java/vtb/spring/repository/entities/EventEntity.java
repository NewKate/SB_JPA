package vtb.spring.repository.entities;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Transactional
@Entity(name = "event")
@Table(name = "event")
public class EventEntity {

    @Id
    @SequenceGenerator(name="seq_opera", sequenceName = "SEQ_GEN_EVENT", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_opera")
    private Integer event_id;
    @JoinColumn(name = "opera_id", referencedColumnName = "opera_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private OperaEntity opera;
    @Column
    private String eventDate;
    @Column
    private Integer total;
    @Column
    private Integer available;
    @Column
    private String status;
    @Column
    private String comment;

    public EventEntity() {
    }

    public EventEntity(OperaEntity opera, String eventDate) {
        this.opera = opera;
        this.eventDate = eventDate;
    }

    public EventEntity(OperaEntity opera, String eventDate, Integer total, Integer available) {
        this.opera = opera;
        this.eventDate = eventDate;
        this.total = total;
        this.available = available;
        this.status = "Актуально";
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OperaEntity getOpera() {
        return opera;
    }

    public void setOpera(OperaEntity opera) {
        this.opera = opera;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
