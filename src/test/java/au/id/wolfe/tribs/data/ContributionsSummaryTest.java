package au.id.wolfe.tribs.data;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ContributionsSummaryTest {

    @Test
    public void testAddingNewUserContributions(){
	
	// When a new author is found in the work log we need to add a new user contribution
	// to the object.
	// author, authorFullName, 
	
	String author, authorFullName;
	
	author = "markw";
	authorFullName = "Mark Wolfe";
	
	boolean created;
	
	ContributionsSummary contributionsSummary = new ContributionsSummary();
	
	assertFalse(contributionsSummary.checkUserContrubitionsExists(author));
	    
	created = contributionsSummary.addUserContribution(author, authorFullName);
	
	assertTrue(created);
	assertTrue(contributionsSummary.checkUserContrubitionsExists(author));
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testUserContributionNotFound(){
	ContributionsSummary contributionsSummary = new ContributionsSummary();
	
	contributionsSummary.getUserContributionByUserId("markw");
    }
}
