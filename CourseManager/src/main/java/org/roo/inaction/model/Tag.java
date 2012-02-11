package org.roo.inaction.model;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import org.roo.inaction.model.Course;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class Tag {

    @NotNull
    @Size(min = 1, max = 25)
    private String tag;

    @NotNull
    @Size(max = 250)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<Course>();
    
    public void addCourse(Course course) {
        getCourses().add(course);
        course.getTags().add(this);
    }
}
