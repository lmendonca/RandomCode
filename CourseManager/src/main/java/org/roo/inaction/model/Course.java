package org.roo.inaction.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.roo.inaction.model.TrainingProgram;
import javax.persistence.ManyToOne;
import java.util.Set;
import org.roo.inaction.model.Tag;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findCoursesByNameLike", "findCoursesByCourseTypeAndRunDateBetween" })
public class Course {

    @Column(name = "course_name")
    @Size(min = 1, max = 60)
    private String name;

    @NotNull
    @Size(max = 1000)
    private String description;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("99999.99")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal listPrice;

    @NotNull
    @Column(name = "max_capacity")
    @Min(1L)
    @Max(9999L)
    private Integer maxiumumCapacity;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date runDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseTypeEnum courseType;

    @ManyToOne
    private TrainingProgram trainingProgram;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    private Set<Tag> tags = new HashSet<Tag>();

    @AssertTrue(message = "Price is invalid - must be between $0 - $10,000 with no fractional values.")
    public boolean isValid() {
        if (listPrice != null) {
            try {
                listPrice.intValueExact();
            } catch (ArithmeticException e) {
                return false;
            }
        }
        return true;
    }
    
    public void addTag(Tag tag) {
        tag.addCourse(this);
    }
}
