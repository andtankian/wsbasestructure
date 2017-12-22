package br.com.wsbasestructure.view.interfaces;

import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.Result;
import br.com.wsbasestructure.dto.interfaces.IHolder;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andrew Ribeiro
 */
public interface IViewHelper {
    public Response setView(Result result);
    public IHolder getView(FlowContainer fc);
    public String getTypeRequest();
    default public void loadBusinessRulesBeforeMainFlow() {}
    default public void loadBusinessRulesAfterMainFlow() {}
    public List getRulesBeforeMainFlow();
    public List getRulesAfterMainFlow();
}
