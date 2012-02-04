package org.roo.inaction.model;

import java.math.BigDecimal;

import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Course.class)
public class CourseDataOnDemand {

	public Course getNewTransientCourse(int index) {
        Course obj = new Course();
        setCourseType(obj, index);
        setDescription(obj, index);
        obj.setListPrice(new BigDecimal("50.0"));
        setMaxiumumCapacity(obj, index);
        setName(obj, index);
        setRunDate(obj, index);
        return obj;
    }
}
