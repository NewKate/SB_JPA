package vtb.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import vtb.spring.model.Opera;

import java.util.Date;

public class EventDto {

    @JsonProperty
    private Opera opera;
    @JsonProperty
    private Date eventDate;
    @JsonProperty
    private Integer total;
    @JsonProperty
    private Integer available;
    @JsonProperty
    private String status;
    @JsonProperty
    private String comment;

    public Opera getOpera() {
        return opera;
    }

    public void setOpera(Opera opera) {
        this.opera = opera;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
