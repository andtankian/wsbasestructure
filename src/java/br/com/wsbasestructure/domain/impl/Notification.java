package br.com.wsbasestructure.domain.impl;

import br.com.wsbasestructure.annotations.DefaultAttribute;
import br.com.wsbasestructure.domain.abstracts.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author Andrew Ribeiro
 */
@javax.persistence.Entity
public class Notification extends Entity {

    public final static String NOTIFICATION = "Notificação";

    public Notification() {
        seen = false;
    }

    @DefaultAttribute
    private String title;
    @DefaultAttribute
    private String description;
    @DefaultAttribute
    private String type;
    @DefaultAttribute
    private String picture;
    @DefaultAttribute
    private String link;
    @DefaultAttribute
    private User user;
    @DefaultAttribute
    private boolean seen;

    @Column(length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length = 10000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(length = 1000)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Column(length = 5000)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
