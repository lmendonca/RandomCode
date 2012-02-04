/**
 * 
 */
package org.junit.inaction.ii;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author leonardo
 * 
 */
@RunWith(value = Suite.class)
@SuiteClasses(value = {TestSuiteA.class, TestSuiteB.class })
public class MasterTestSuite {

}
