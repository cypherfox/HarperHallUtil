package de.harper_hall.util.collections.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;

import de.harper_hall.util.collections.IntervalArrayHashMap;
import de.harper_hall.util.collections.IntervalMap;

/**
 * Generated code for the test suite <b>IntervalArrayHashMapTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/collections/tests/IntervalArrayHashMapTest.testsuite</i>.
 */
public class IntervalArrayHashMapTest extends HyadesTestCase {
	/**
	 * Constructor for IntervalArrayHashMapTest.
	 * @param name
	 */
	public IntervalArrayHashMapTest(String name) {
		super(name);
	}

	/**
	 * Returns the JUnit test suite that implements the <b>IntervalArrayHashMapTest</b>
	 * definition.
	 */
	public static Test suite() {
		HyadesTestSuite intervalArrayHashMapTest = new HyadesTestSuite(
				"IntervalArrayHashMapTest");
		intervalArrayHashMapTest.setArbiter(DefaultTestArbiter.INSTANCE).setId(
				"FD5AB9783D3626DF1792C1B033A011DC");
	
		intervalArrayHashMapTest.addTest(new IntervalArrayHashMapTest(
				"testIntervalRetrieval").setId("FD5AB9783D3626DF281A04D033A011DC")
				.setTestInvocationId("FD5AB9783D3626DF4F784BE033A011DC"));
		return intervalArrayHashMapTest;
	}

	IntervalMap<Integer, String> map = new IntervalArrayHashMap<Integer, String>();
	
	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		// load some values
		map.clear();
		map.put(1, "one");
		map.put(5, "five");
		map.put(10, "ten");
		
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
	}

	/**
	* testIntervalRetrieval
	*
	* Try various keys when retrieving from the container.
	* 
	* @throws Exception
	*/
	public void testIntervalRetrieval()
	throws Exception
	{
		assertEquals("five", map.get(2));
		assertEquals("one", map.get(0));
		map.setLastForBeyond(false);
		assertEquals(null, map.get(11));
		map.setLastForBeyond(true);
		assertEquals("ten", map.get(11));
	}

}
