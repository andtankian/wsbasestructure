package br.com.wsbasestructure.rules.impl;

import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.interfaces.IHolder;
import br.com.wsbasestructure.rules.abstracts.AbstractCommand;

/**
 *
 * @author Andrew Ribeiro
 */
public class ValidateIDEntityCommand extends AbstractCommand{

    public ValidateIDEntityCommand() {
        super();
    }    

    @Override
    public IHolder exe(IHolder holder, FlowContainer flowContainer) {

        /*DON'T FORGET TO CALL SUPER!*/
        super.exe(holder, flowContainer);
        
        Long id = e.getId();
        
        if(id == null || id == 0){
            m.setError("invalid id");
            isValid = false;
        }
        
        return holder;
        
    }
    
}
