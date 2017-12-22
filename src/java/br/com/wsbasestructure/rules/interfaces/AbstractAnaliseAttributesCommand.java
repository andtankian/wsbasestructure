package br.com.wsbasestructure.rules.interfaces;

import java.util.List;

/**
 *
 * @author Andrew Ribeiro
 */
public abstract class AbstractAnaliseAttributesCommand implements ICommand{

    protected List rejects;
    protected List accepts;
    public AbstractAnaliseAttributesCommand(List accepts, List rejects) {
        this.accepts = accepts;
        this.rejects = rejects;
    }
    
    
    
}
