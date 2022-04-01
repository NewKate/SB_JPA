package vtb.spring.events;

import org.springframework.context.ApplicationEvent;

public class OperaEvent extends ApplicationEvent {

    public OperaEvent(Info info) {
        super(info);
    }

    public static class Info{
        private String ticketInfo;

        public Info(String ticketInfo) {
            this.ticketInfo = ticketInfo;
        }

        public String getTicketInfo() {
            return ticketInfo;
        }

        public void setTicketInfo(String ticketInfo) {
            this.ticketInfo = ticketInfo;
        }
    }
}
