package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static void main(String[] args) {
    junit.textui.TestRunner.run(AllTests.suite());
  }

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for test");
    //$JUnit-BEGIN$
    suite.addTest(new TestSuite(TestR1_RawMaterials.class));
    suite.addTest(new TestSuite(TestR2_Products.class));
    suite.addTest(new TestSuite(TestR3_Recipes.class));
    suite.addTest(new TestSuite(TestR4_Menu.class));
    //$JUnit-END$
    return suite;
  }
}
