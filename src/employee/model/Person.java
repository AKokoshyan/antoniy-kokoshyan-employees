package employee.model;

import java.time.LocalDate;

public class Person {
    private Integer id;
    private Integer projectId;
    private LocalDate from;
    private LocalDate to;

    public Person(String id, String projectId, LocalDate from, LocalDate to){
        this.id = Integer.valueOf(id);
        this.projectId = Integer.valueOf(projectId);
        this.from = from;
        this.to = to;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Person id " + this.id + " project id " + this.projectId + " start date " + this.from + " end date " + this.to;
    }
}