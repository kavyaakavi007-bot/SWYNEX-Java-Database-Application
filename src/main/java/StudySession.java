import java.time.LocalDate;

public class StudySession {

    private int id;
    private String subject;
    private String topic;
    private int duration;
    private Priority priority;
    private Status status;
    private LocalDate sessionDate;

    public StudySession(int id, String subject, String topic,
                        int duration, Priority priority,
                        Status status, LocalDate sessionDate) {
        this.id = id;
        this.subject = subject;
        this.topic = topic;
        this.duration = duration;
        this.priority = priority;
        this.status = status;
        this.sessionDate = sessionDate;
    }

    public StudySession(String subject, String topic,
                        int duration, Priority priority,
                        Status status, LocalDate sessionDate) {
        this(0, subject, topic, duration, priority, status, sessionDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public int getDuration() {
        return duration;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Subject: " + subject +
                ", Topic: " + topic +
                ", Duration: " + duration + " minutes" +
                ", Priority: " + priority +
                ", Status: " + status +
                ", Date: " + sessionDate;
    }
}