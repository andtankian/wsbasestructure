package br.com.wsbasestructure.view.abstracts;

import br.com.wsbasestructure.domain.abstracts.Entity;
import br.com.wsbasestructure.dto.FlowContainer;
import br.com.wsbasestructure.dto.Result;
import br.com.wsbasestructure.dto.interfaces.IHolder;
import br.com.wsbasestructure.view.impl.GenericExclusionStrategy;
import br.com.wsbasestructure.view.interfaces.IViewHelper;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andrew Ribeiro
 */
public abstract class AbstractViewHelper implements IViewHelper {

    public AbstractViewHelper() {
        rulesBeforeMainFlow = new ArrayList();
        rulesAfterMainFlow = new ArrayList();
        rejects = new ArrayList();
        accepts = new ArrayList();
    }
    private final List rulesBeforeMainFlow;
    private final List rulesAfterMainFlow;
    private Set ignore;
    protected String typeRequest;
    protected List rejects;
    protected List accepts;

    @Override
    public IHolder getView(FlowContainer fc) {
        List l = fc.getCr().getUriInfo().getQueryParameters().get("treq");
        typeRequest = l != null ? (String) l.get(0) : "crud";
        loadBusinessRulesBeforeMainFlow();
        loadBusinessRulesAfterMainFlow();
        return null;
    }

    @Override
    public String getTypeRequest() {
        return typeRequest;
    }

    @Override
    public List getRulesBeforeMainFlow() {
        return rulesBeforeMainFlow;
    }

    @Override
    public List getRulesAfterMainFlow() {
        return rulesAfterMainFlow;
    }

    @Override
    public Response setView(Result result) {
        GsonBuilder gb = new GsonBuilder();
        Entity e = (Entity) result.getHolder().getEntities().get(0);
        prepareReturnFields(e.getAllFields(), e.getDefaultAcceptedFields());
        gb.addSerializationExclusionStrategy(new GenericExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                return ignore.contains(fa.getName());
            }
        });
        Gson g = gb.create();

        return Response.status(Response.Status.OK).entity(g.toJson(result)).build();
    }
    
    private void prepareReturnFields(List all, List wannotations){
        all.removeAll(wannotations);
        all.removeAll(accepts);
        all.retainAll(rejects);
        
        ignore = new HashSet(all);
    }

}
