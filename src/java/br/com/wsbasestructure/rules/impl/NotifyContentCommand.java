package br.com.wsbasestructure.rules.impl;

import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.interfaces.IHolder;
import br.com.wsbasestructure.endpoints.sessions.interfaces.WebSocketSessionHandler;
import br.com.wsbasestructure.rules.interfaces.AbstractAnaliseAttributesCommand;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andrew Ribeiro
 */
public class NotifyContentCommand extends AbstractAnaliseAttributesCommand{

    public NotifyContentCommand(List accepts, List rejects) {
        super(accepts, rejects);
    }

    
    @Override
    public IHolder exe(IHolder holder, FlowContainer flowContainer) {
        HttpServletRequest r = flowContainer.getHttprequest();
        WebSocketSessionHandler ws = (WebSocketSessionHandler)r.getServletContext().getAttribute("contentep");
        if(ws != null){
            if(flowContainer.getResult().getStatus().equals(Response.Status.OK)){
                ws.notify(flowContainer, this.rejects);
            }
        }
        
        return holder;
    }
    
}
