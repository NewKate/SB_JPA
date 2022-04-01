package vtb.spring.repository.entities;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
@Entity(name = "opera")
@Table(name = "opera")
public class OperaEntity {

    @Id
    @SequenceGenerator(name="seq_opera", sequenceName = "SEQ_GEN", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_opera")
    private Integer opera_id;
    @Column
    private String label;
    @Column
    private Integer age;
    @Column
    private String type;
    @Column
    private String descriptoin;
    @Column
    private String duration;
    @Column
    private Integer intermission;

    @OneToMany(mappedBy = "opera", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventEntity> events;

    public OperaEntity() {
    }

    public OperaEntity(String label, Integer age, String type) {
        this.label = label;
        this.age = age;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOpera_id() {
        return opera_id;
    }

    public void setOpera_id(Integer opera_id) {
        this.opera_id = opera_id;
    }

    public String getDescriptoin() {
        return descriptoin;
    }

    public void setDescriptoin(String descriptoin) {
        this.descriptoin = descriptoin;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getIntermission() {
        return intermission;
    }

    public void setIntermission(Integer intermission) {
        this.intermission = intermission;
    }

}
