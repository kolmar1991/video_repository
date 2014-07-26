/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reflect;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author piotrek
 */
public class ClassManagerTest {

    @Test
    public void loadTest() throws Exception {
        Class clazz = new ClassManager().load("src/main/resources/ConverterExample-1.0-SNAPSHOT.jar", "newpackage.newpackage2.New");
        System.out.println(clazz.getSimpleName());
        assertNotNull(clazz);
        assertEquals("New", clazz.getSimpleName());
    }
    
}
