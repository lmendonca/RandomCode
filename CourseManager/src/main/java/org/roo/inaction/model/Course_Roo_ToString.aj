// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.roo.inaction.model;

import java.lang.String;

privileged aspect Course_Roo_ToString {
    
    public String Course.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CourseType: ").append(getCourseType()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("ListPrice: ").append(getListPrice()).append(", ");
        sb.append("MaxiumumCapacity: ").append(getMaxiumumCapacity()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("RunDate: ").append(getRunDate()).append(", ");
        sb.append("Tags: ").append(getTags() == null ? "null" : getTags().size()).append(", ");
        sb.append("TrainingProgram: ").append(getTrainingProgram()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Valid: ").append(isValid());
        return sb.toString();
    }
    
}
