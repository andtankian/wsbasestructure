package br.com.wsbasestructure.dto;

import br.com.wsbasestructure.dto.interfaces.IHolder;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andrew Ribeiro
 */
public class Result {

    private IHolder holder;
    private Message message;
    private Response.Status status;

    public Result() {

        this.message = new Message();
    }

    public IHolder getHolder() {
        return holder;
    }

    public void setHolder(IHolder holder) {
        this.holder = holder;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

}
