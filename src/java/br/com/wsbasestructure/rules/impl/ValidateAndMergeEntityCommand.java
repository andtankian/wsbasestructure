package br.com.wsbasestructure.rules.impl;

import br.com.wsbasestructure.rules.abstracts.AbstractCommand;
import br.com.wsbasestructure.domain.abstracts.Entity;
import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.interfaces.IHolder;
import org.hibernate.Session;

/**
 *
 * @author Andrew Ribeiro
 */
public class ValidateAndMergeEntityCommand extends AbstractCommand {

    public ValidateAndMergeEntityCommand() {
        super();
    }

    @Override
    public IHolder exe(IHolder holder, FlowContainer flowContainer) {
        super.exe(holder, flowContainer);
        Session s = flowContainer.getSession();
        Entity loaded = (Entity) s.get(e.getClass(), e.getId());
        if (loaded != null) {
            try {
                loaded.merge(e);
            } catch (Exception ex) {
                m.setError("merge error");
                isValid = false;
            }
        } else {
            m.setError("not found");
            isValid = false;
        }

        return holder;
    }

}
