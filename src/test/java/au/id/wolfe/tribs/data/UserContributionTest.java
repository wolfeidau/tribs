package au.id.wolfe.tribs.data;

import junit.framework.Assert;

import org.junit.Test;

public class UserContributionTest {

	@Test
	public void testAddOfProjectTimeContributions(){
		
		ProjectTimeSpent phs = new ProjectTimeSpent("STAR", "Star Project", 60l);
		
		UserContribution uc = new UserContribution();
		
		uc.addProjectHours("STAR", "Star Project", 60l);
		
		Assert.assertEquals(true, uc.getProjectTimeSpentList().contains(phs));
		
	}
	
}
