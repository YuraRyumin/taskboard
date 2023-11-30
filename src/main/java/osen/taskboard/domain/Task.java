package osen.taskboard.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "parent")
    private Task parent;

    @Column(name = "uuid")
    private String uuid;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "done")
    private boolean done;

    public Task() {
    }

    public Task(String header, String description, Task parent, String uuid, User user, boolean done) {
        this.header = header;
        this.description = description;
        this.parent = parent;
        this.uuid = uuid;
        this.user = user;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
