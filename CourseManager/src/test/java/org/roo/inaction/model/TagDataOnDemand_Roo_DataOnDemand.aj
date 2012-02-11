// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.roo.inaction.model;

import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.roo.inaction.model.Tag;
import org.springframework.stereotype.Component;

privileged aspect TagDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TagDataOnDemand: @Component;
    
    private Random TagDataOnDemand.rnd = new SecureRandom();
    
    private List<Tag> TagDataOnDemand.data;
    
    public Tag TagDataOnDemand.getNewTransientTag(int index) {
        Tag obj = new Tag();
        setDescription(obj, index);
        setTag(obj, index);
        return obj;
    }
    
    public void TagDataOnDemand.setDescription(Tag obj, int index) {
        String description = "description_" + index;
        if (description.length() > 250) {
            description = description.substring(0, 250);
        }
        obj.setDescription(description);
    }
    
    public void TagDataOnDemand.setTag(Tag obj, int index) {
        String tag = "tag_" + index;
        if (tag.length() > 25) {
            tag = tag.substring(0, 25);
        }
        obj.setTag(tag);
    }
    
    public Tag TagDataOnDemand.getSpecificTag(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Tag obj = data.get(index);
        return Tag.findTag(obj.getId());
    }
    
    public Tag TagDataOnDemand.getRandomTag() {
        init();
        Tag obj = data.get(rnd.nextInt(data.size()));
        return Tag.findTag(obj.getId());
    }
    
    public boolean TagDataOnDemand.modifyTag(Tag obj) {
        return false;
    }
    
    public void TagDataOnDemand.init() {
        data = Tag.findTagEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Tag' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<org.roo.inaction.model.Tag>();
        for (int i = 0; i < 10; i++) {
            Tag obj = getNewTransientTag(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
