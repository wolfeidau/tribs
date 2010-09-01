package au.id.wolfe.tribs.data;

import static junit.framework.Assert.*;

import org.junit.Test;

public class ProjectTimeSpentTest {

	@Test 
	public void testAdditionOfTime(){
		ProjectTimeSpent pts = new ProjectTimeSpent("Hello project", "HP", 100l);
		
		assertEquals(100l, pts.getTimespent().longValue());
		
		pts.addHours(100l);
		
		assertEquals(200l, pts.getTimespent().longValue());
	}
	
	@Test
	public void testProjectTimeSpentEquals(){
		ProjectTimeSpent pts1 = new ProjectTimeSpent("Hello project", "HP", 100l);
		
		ProjectTimeSpent pts2 = new ProjectTimeSpent("Hello project", "HP", 100l);
		
		assertEquals(pts1, pts2);
	}
	
}
