// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.roo.inaction.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.roo.inaction.model.TrainingProgramDataOnDemand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TrainingProgramIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TrainingProgramIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TrainingProgramIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: TrainingProgramIntegrationTest: @Transactional;
    
    @Autowired
    private TrainingProgramDataOnDemand TrainingProgramIntegrationTest.dod;
    
    @Test
    public void TrainingProgramIntegrationTest.testCountTrainingPrograms() {
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", dod.getRandomTrainingProgram());
        long count = org.roo.inaction.model.TrainingProgram.countTrainingPrograms();
        org.junit.Assert.assertTrue("Counter for 'TrainingProgram' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TrainingProgramIntegrationTest.testFindTrainingProgram() {
        org.roo.inaction.model.TrainingProgram obj = dod.getRandomTrainingProgram();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to provide an identifier", id);
        obj = org.roo.inaction.model.TrainingProgram.findTrainingProgram(id);
        org.junit.Assert.assertNotNull("Find method for 'TrainingProgram' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'TrainingProgram' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TrainingProgramIntegrationTest.testFindAllTrainingPrograms() {
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", dod.getRandomTrainingProgram());
        long count = org.roo.inaction.model.TrainingProgram.countTrainingPrograms();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'TrainingProgram', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<org.roo.inaction.model.TrainingProgram> result = org.roo.inaction.model.TrainingProgram.findAllTrainingPrograms();
        org.junit.Assert.assertNotNull("Find all method for 'TrainingProgram' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'TrainingProgram' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TrainingProgramIntegrationTest.testFindTrainingProgramEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", dod.getRandomTrainingProgram());
        long count = org.roo.inaction.model.TrainingProgram.countTrainingPrograms();
        if (count > 20) count = 20;
        java.util.List<org.roo.inaction.model.TrainingProgram> result = org.roo.inaction.model.TrainingProgram.findTrainingProgramEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'TrainingProgram' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'TrainingProgram' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TrainingProgramIntegrationTest.testFlush() {
        org.roo.inaction.model.TrainingProgram obj = dod.getRandomTrainingProgram();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to provide an identifier", id);
        obj = org.roo.inaction.model.TrainingProgram.findTrainingProgram(id);
        org.junit.Assert.assertNotNull("Find method for 'TrainingProgram' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyTrainingProgram(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'TrainingProgram' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TrainingProgramIntegrationTest.testMerge() {
        org.roo.inaction.model.TrainingProgram obj = dod.getRandomTrainingProgram();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to provide an identifier", id);
        obj = org.roo.inaction.model.TrainingProgram.findTrainingProgram(id);
        boolean modified =  dod.modifyTrainingProgram(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        org.roo.inaction.model.TrainingProgram merged =  obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'TrainingProgram' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TrainingProgramIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", dod.getRandomTrainingProgram());
        org.roo.inaction.model.TrainingProgram obj = dod.getNewTransientTrainingProgram(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'TrainingProgram' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'TrainingProgram' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TrainingProgramIntegrationTest.testRemove() {
        org.roo.inaction.model.TrainingProgram obj = dod.getRandomTrainingProgram();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'TrainingProgram' failed to provide an identifier", id);
        obj = org.roo.inaction.model.TrainingProgram.findTrainingProgram(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'TrainingProgram' with identifier '" + id + "'", org.roo.inaction.model.TrainingProgram.findTrainingProgram(id));
    }
    
}
