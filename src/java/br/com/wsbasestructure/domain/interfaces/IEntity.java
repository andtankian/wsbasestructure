package br.com.wsbasestructure.domain.interfaces;

import br.com.wsbasestructure.domain.abstracts.Entity;
import java.util.List;

/**
 *
 * @author andrew
 */
public interface IEntity {
    public void merge(Entity e) throws Exception;
    public List getDefaultAcceptedFields();
    public List getAllFields();
}
