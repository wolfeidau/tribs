package au.id.wolfe.tribs.data;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class UserContributionTest {

	@Test
	public void testAddOfProjectTimeContributions() {

		boolean added;

		ProjectTimeSpent phs = new ProjectTimeSpent("STAR", "Star Project", 60l);

		UserContribution uc = new UserContribution("markw", "Mark Wolfe");

		added = uc.addOrUpdateProjectHours("STAR", "Star Project", 60l);

		assertTrue(added);

		assertEquals(true, uc.getProjectTimeSpentList().contains(phs));

		assertEquals(1, uc.projectTimeSpentList.size());
		
		added = uc.addOrUpdateProjectHours("STAR", "Star Project", 50l);

		assertFalse(added);

		phs.setTimespent(110l);

		assertTrue(uc.getProjectTimeSpentList().contains(phs));
		
		assertEquals(1, uc.projectTimeSpentList.size());

	}

}
