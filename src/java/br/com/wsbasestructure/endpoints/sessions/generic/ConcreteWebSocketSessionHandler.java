package br.com.wsbasestructure.endpoints.sessions.generic;

import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.NotificationSystemContainer;
import br.com.wsbasestructure.endpoints.sessions.interfaces.CustomSessionHolder;
import br.com.wsbasestructure.endpoints.sessions.interfaces.WebSocketSessionHandler;
import br.com.wsbasestructure.view.impl.GenericExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.websocket.Session;

/**
 *
 * @author Andrew Ribeiro
 */
public class ConcreteWebSocketSessionHandler implements WebSocketSessionHandler {

    private final Map<Session, CustomSessionHolder> sessions;

    public ConcreteWebSocketSessionHandler() {
        this.sessions = new HashMap<>();
    }

    @Override
    public synchronized void add(Session session, String extra) {
        session.setMaxIdleTimeout(-1);
        JsonObject j = (JsonObject) new JsonParser().parse(extra);
        sessions.put(session, new CustomSessionHolder(session, j.get("id").getAsLong(), j.get("entity").getAsString()));
    }

    @Override
    public synchronized void remove(Session session) {
        this.sessions.remove(session);
    }

    @Override
    public Map getSessions() {
        return sessions;
    }

    @Override
    public synchronized void notify(FlowContainer fc, List ignore) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new GenericExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                return ignore.contains(fa.getName());
            }
        }).create();
        JsonObject g = (JsonObject) gson.toJsonTree(fc.getResult());
        g.addProperty("action", fc.getCr().getMethod());
        String message = g.toString();
        for (Map.Entry<Session, CustomSessionHolder> entry : sessions.entrySet()) {
            if (fc.getResult().getHolder().getEntities().get(0).getClass().getName().equalsIgnoreCase(entry.getValue().getEntity())) {
                entry.getKey().getAsyncRemote().sendText(message);
            }
        }
    }

    @Override
    public synchronized void notify(NotificationSystemContainer nsc) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new GenericExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                return fa.getName().equals("groups") || fa.getName().equals("ticket") || fa.getName().equals("user");
            }
        }).create();
        JsonObject g = (JsonObject) gson.toJsonTree(nsc);
        g.addProperty("header", nsc.getClass().getName());
        g.addProperty("action", "notify");
        String message = g.toString();
    }

}
