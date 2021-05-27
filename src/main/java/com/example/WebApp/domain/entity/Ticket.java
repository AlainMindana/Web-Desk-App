package com.example.WebApp.domain.entity;

import com.example.WebApp.domain.reference.Severity;
import com.example.WebApp.domain.reference.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {


    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "severity")
    private Severity severity;

    @Column(name = "status")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigneeId", referencedColumnName = "id")
    private Employee assignee;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TICKET_WATCHERS",
                joinColumns = {@JoinColumn(name = "ticket_id")},
                inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Employee> watchers = new ArrayList<>();

    public Long getTicket() {
        return ticket;
    }

    public void setTicket(Long ticket) {
        this.ticket = ticket;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public List<Employee> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<Employee> watchers) {
        this.watchers = watchers;
    }
}
