// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.roo.inaction.model;

import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;
import org.roo.inaction.model.Student;

privileged aspect Student_Roo_Entity {
    
    declare @type: Student: @Entity;
    
    public static long Student.countStudents() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Student o", Long.class).getSingleResult();
    }
    
    public static List<Student> Student.findAllStudents() {
        return entityManager().createQuery("SELECT o FROM Student o", Student.class).getResultList();
    }
    
    public static Student Student.findStudent(Long id) {
        if (id == null) return null;
        return entityManager().find(Student.class, id);
    }
    
    public static List<Student> Student.findStudentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Student o", Student.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
