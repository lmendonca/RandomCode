// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.roo.inaction.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.roo.inaction.model.InstructorDataOnDemand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect InstructorIntegrationTest_Roo_IntegrationTest {
    
    declare @type: InstructorIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: InstructorIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: InstructorIntegrationTest: @Transactional;
    
    @Autowired
    private InstructorDataOnDemand InstructorIntegrationTest.dod;
    
    @Test
    public void InstructorIntegrationTest.testCountInstructors() {
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", dod.getRandomInstructor());
        long count = org.roo.inaction.model.Instructor.countInstructors();
        org.junit.Assert.assertTrue("Counter for 'Instructor' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void InstructorIntegrationTest.testFindInstructor() {
        org.roo.inaction.model.Instructor obj = dod.getRandomInstructor();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to provide an identifier", id);
        obj = org.roo.inaction.model.Instructor.findInstructor(id);
        org.junit.Assert.assertNotNull("Find method for 'Instructor' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Instructor' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void InstructorIntegrationTest.testFindAllInstructors() {
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", dod.getRandomInstructor());
        long count = org.roo.inaction.model.Instructor.countInstructors();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Instructor', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<org.roo.inaction.model.Instructor> result = org.roo.inaction.model.Instructor.findAllInstructors();
        org.junit.Assert.assertNotNull("Find all method for 'Instructor' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Instructor' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void InstructorIntegrationTest.testFindInstructorEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", dod.getRandomInstructor());
        long count = org.roo.inaction.model.Instructor.countInstructors();
        if (count > 20) count = 20;
        java.util.List<org.roo.inaction.model.Instructor> result = org.roo.inaction.model.Instructor.findInstructorEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Instructor' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Instructor' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void InstructorIntegrationTest.testFlush() {
        org.roo.inaction.model.Instructor obj = dod.getRandomInstructor();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to provide an identifier", id);
        obj = org.roo.inaction.model.Instructor.findInstructor(id);
        org.junit.Assert.assertNotNull("Find method for 'Instructor' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyInstructor(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Instructor' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void InstructorIntegrationTest.testMerge() {
        org.roo.inaction.model.Instructor obj = dod.getRandomInstructor();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to provide an identifier", id);
        obj = org.roo.inaction.model.Instructor.findInstructor(id);
        boolean modified =  dod.modifyInstructor(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        org.roo.inaction.model.Instructor merged = (org.roo.inaction.model.Instructor) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Instructor' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void InstructorIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", dod.getRandomInstructor());
        org.roo.inaction.model.Instructor obj = dod.getNewTransientInstructor(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Instructor' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Instructor' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void InstructorIntegrationTest.testRemove() {
        org.roo.inaction.model.Instructor obj = dod.getRandomInstructor();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Instructor' failed to provide an identifier", id);
        obj = org.roo.inaction.model.Instructor.findInstructor(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Instructor' with identifier '" + id + "'", org.roo.inaction.model.Instructor.findInstructor(id));
    }
    
}