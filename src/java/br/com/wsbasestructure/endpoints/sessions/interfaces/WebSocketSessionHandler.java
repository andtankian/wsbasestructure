package br.com.wsbasestructure.endpoints.sessions.interfaces;

import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.NotificationSystemContainer;
import java.util.List;
import java.util.Map;
import javax.websocket.Session;

/**
 *
 * @author Andrew Ribeiro
 */
public interface WebSocketSessionHandler {
    
    public void add(Session session, String extra);
    public void notify(FlowContainer fc, List ignore);
    public void notify(NotificationSystemContainer nsc);
    public void remove(Session session);
    public Map getSessions();
}
