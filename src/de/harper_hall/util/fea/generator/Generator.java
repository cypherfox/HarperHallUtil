/**
 * 
 */
package de.harper_hall.util.fea.generator;

import java.lang.reflect.Method;

import de.harper_hall.util.fea.annotations.*;

/**
 * @author sage
 *
 */
public class Generator {
	static Class[] feaClasses = {FeaAll.class,FeaCreate.class,FeaRead.class,FeaUpdate.class,FeaDelete.class,
		                         FeaOwnerAll.class,FeaOwnerCreate.class,FeaOwnerRead.class,FeaOwnerUpdate.class,FeaOwnerDelete.class}; 
	
	public String[] getAllRoles(Class classToCheck) {
		java.util.ArrayList<String> retval = new java.util.ArrayList<String>();

		for (Method m : classToCheck.getMethods()) {
			for (Class ac : feaClasses)
				if (m.isAnnotationPresent(ac)) {
					FeaAll fa = (FeaAll) m.getAnnotation(FeaAll.class);
					for (int i = 0; i < fa.value().length; i++)
						retval.add(fa.value()[i]);
				}
		}

		return retval.toArray(new String[retval.size()]);
	}
	
	public static void main(String[] args) throws Exception {
	      int passed = 0, failed = 0;
	      
	      System.out.printf("Passed: %d, Failed %d%n", passed, failed);
	   }
}
