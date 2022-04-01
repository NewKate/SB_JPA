package vtb.spring.model;

import java.util.Date;

public class Event {

    private Opera opera;
    private Date eventDate;
    private Integer total;
    private Integer available;
    private String status;
    private String comment;

    public Event(Opera opera, Date eventDate) {
        this.opera = opera;
        this.eventDate = eventDate;
    }

    public Event(Opera opera, Date eventDate, Integer total, Integer available) {
        this.opera = opera;
        this.eventDate = eventDate;
        this.total = total;
        this.available = available;
        this.status = "Актуально";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
