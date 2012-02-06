package org.roo.inaction.model;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Set;
import org.roo.inaction.model.Course;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class TrainingProgram {

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingProgram")
    private Set<Course> courses = new HashSet<Course>();
}
