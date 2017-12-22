package br.com.wsbasestructure.endpoints.sessions.interfaces;

import javax.websocket.Session;

/**
 *
 * @author andrew
 */
public class CustomSessionHolder {

    public CustomSessionHolder(Session session, Long id, String entity) {
        this.session = session;
        this.id = id;
        this.entity = entity;
    }
    
    
    private Session session;
    private Long id;
    private String entity;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
