package vtb.spring.model;

import java.util.Date;

public class Ticket {

    private Event event;
    private Integer price;
    private Integer row;
    private Integer place;

    public Ticket(Event event, Integer price, Integer row, Integer place) {
        this.event = event;
        this.price = price;
        this.row = row;
        this.place = place;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }
}
