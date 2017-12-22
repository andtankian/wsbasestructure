package br.com.wsbasestructure.domain.abstracts;

import br.com.wsbasestructure.domain.interfaces.IEntity;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import br.com.wsbasestructure.annotations.DefaultAttribute;
import javax.persistence.Transient;

/**
 *
 * @author Andrew Ribeiro
 */
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entity implements IEntity, Serializable {

    @DefaultAttribute
    protected Long id;
    @DefaultAttribute
    protected Timestamp dateReg;
    @DefaultAttribute
    protected String status;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateReg() {
        return dateReg;
    }

    public void setDateReg(Timestamp dateReg) {
        this.dateReg = dateReg != null ? dateReg : new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void merge(Entity e) throws Exception {

        Class thisClass = this.getClass();
        Class thatClass = e.getClass();

        if (!thisClass.getName().equals(thatClass.getName())) {
            return;
        }

        /*WORKING WITH THIS ATTRIBUTES
          GETTING THIS CLASS ATTRIBUTES (INCLUDING SUPERCLASSES)*/
        Stack thisResult = new Stack();
        for (; thisClass != null; thisClass = thisClass.getSuperclass()) {
            Field[] fields = thisClass.getDeclaredFields();
            for (Field classField : fields) {
                classField.setAccessible(true);
                thisResult.add(classField);
            }
        }


        /*GETTING CLASS ATTRIBUTES PASSED BY PARAMETER (INCLUDING SUPERCLASSES)*/
        Stack thatResult = new Stack();
        for (; thatClass != null; thatClass = thatClass.getSuperclass()) {
            Field[] fields = thatClass.getDeclaredFields();
            for (Field classField : fields) {
                classField.setAccessible(true);
                thatResult.add(classField);
            }
        }

        /*SETTING VALUES ATTRIBUTES PASSED BY PARAMATER TO THIS INSTANCE IF IT IS NOT NULL*/
        for (int i = 0; i < thisResult.size(); i++) {
            Field f = (Field) thatResult.get(i);
            Object obj = f.get(e);
            if (obj != null) {
                ((Field) thisResult.get(i)).set(this, obj);
            }
        }
    }

    @Override
    @Transient
    public List getDefaultAcceptedFields() {        
        Class c = this.getClass();
        List accepts = new ArrayList();
        for (; c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                DefaultAttribute [] rf = field.getAnnotationsByType(DefaultAttribute.class);
                if(rf.length > 0){
                    accepts.add(field.getName());
                }
            }
        }
        
        return accepts;
    }

    @Override
    @Transient
    public List getAllFields() {
        Class c = this.getClass();
        List all = new ArrayList();
        for (; c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                all.add(field.getName());
            }
        }
        
        return all;
    }
    
    
    

}
