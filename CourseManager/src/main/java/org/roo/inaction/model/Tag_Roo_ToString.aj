// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.roo.inaction.model;

import java.lang.String;

privileged aspect Tag_Roo_ToString {
    
    public String Tag.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Courses: ").append(getCourses() == null ? "null" : getCourses().size()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Tag: ").append(getTag()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
