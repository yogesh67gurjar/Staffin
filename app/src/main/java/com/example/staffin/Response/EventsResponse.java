package com.example.staffin.Response;

public class EventsResponse {
        private String status;
        private String message;
        private EventsResult eventsResult;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventsResult getEventsResult() {
        return eventsResult;
    }

    public void setEventsResult(EventsResult eventsResult) {
        this.eventsResult = eventsResult;
    }
}
