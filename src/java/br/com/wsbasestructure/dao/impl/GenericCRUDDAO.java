package br.com.wsbasestructure.dao.impl;

import br.com.wsbasestructure.dao.abstracts.AbstractDAO;
import br.com.wsbasestructure.dao.interfaces.ICRUD;
import br.com.wsbasestructure.dto.FlowControl;
import br.com.wsbasestructure.dto.Result;
import br.com.wsbasestructure.dto.interfaces.IHolder;
import javax.ws.rs.core.Response;
import org.hibernate.Session;

/**
 *
 * @author Andrew Ribeiro
 */
public abstract class GenericCRUDDAO extends AbstractDAO implements ICRUD {

    public GenericCRUDDAO(Session session, IHolder holder) {
        super(session, holder);
    }

    @Override
    public Result perform(FlowControl fc, Result result, String method) {
        super.perform(fc, result, method);
        if (method.equalsIgnoreCase("post")) {
            return create();
        } else if (method.equalsIgnoreCase("get")) {
            return read();
        } else if (method.equalsIgnoreCase("put")) {
            return update();
        } else {
            return delete();
        }
    }

    @Override
    public Result create() {
        try {
            session.persist(holder.getEntities().get(0));
            session.getTransaction().commit();
            message.setText("inserted");
            result.setStatus(Response.Status.OK);
        } catch (Exception e) {
            message.setError(e.getMessage());
            result.setStatus(Response.Status.CONFLICT);
            fc.setMustContinue(false);
        } finally {
            result.setHolder(holder);
            result.setMessage(message);
        }

        return result;
    }

    @Override
    public Result update() {
        try {
            session.update(holder.getEntities().get(0));
            session.getTransaction().commit();
            message.setText("updated");
            result.setStatus(Response.Status.OK);
        } catch (Exception e) {
            message.setError(e.getMessage());
            result.setStatus(Response.Status.CONFLICT);
            fc.setMustContinue(false);
        } finally {
            result.setHolder(holder);
            result.setMessage(message);
        }

        return result;
    }

    @Override
    public Result delete() {
        return update();
    }

}
