package br.com.wsbasestructure.rules.abstracts;

import br.com.wsbasestructure.domain.abstracts.Entity;
import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.Message;
import br.com.wsbasestructure.dto.interfaces.IHolder;
import br.com.wsbasestructure.rules.interfaces.ICommand;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andrew Ribeiro
 */
public abstract class AbstractCommand implements ICommand{
    
    protected Message m;
    protected boolean isValid;
    protected Entity e;

    /***
     * Constructor
     */
    public AbstractCommand() {        
        m = new Message();
        isValid = true;
    }
    
    

    /***
     * This method is used to perform repetitive operations when creating rules.
     * 
     * User always have to call this method returning the specific method implementation.
     * 
     * @param holder Used to get all data coming from view helper and more infos
     * @param flowContainer Used to wrap and carry all the sys flow
     * @return holder.
     */
    @Override
    public IHolder exe(IHolder holder, FlowContainer flowContainer) {
        
        e = (Entity) holder.getEntities().get(0);
        
        flowContainer.getResult().setMessage(m); //Set the current message to result object
        flowContainer.getResult().setHolder(holder); //Set the current holder to result object
        
        /*Checks if all operations until this flow do not altered the isValid boolean variable*/
        if(!isValid){
            /*If altered (false) the sys flow must stops and immediately.*/
            flowContainer.getResult().setStatus(Response.Status.PRECONDITION_FAILED);
            flowContainer.getFc().setMustContinue(false);                    
        }
        
        return holder;
    }
    
    
}
