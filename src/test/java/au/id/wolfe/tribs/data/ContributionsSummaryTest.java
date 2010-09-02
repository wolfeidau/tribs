package au.id.wolfe.tribs.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ContributionsSummaryTest {

	@Test
	public void testAddingNewUserContributions() {

		// When a new author is found in the work log we need to add a new user
		// contribution
		// to the object.
		// author, authorFullName,

		String author, authorFullName;

		author = "markw";
		authorFullName = "Mark Wolfe";

		boolean created;

		ContributionsSummary contributionsSummary = new ContributionsSummary();

		assertFalse(contributionsSummary.checkUserContribitionsExists(author));

		created = contributionsSummary.addUserContribution("markw",
				"Mark Wolfe");

		assertTrue(created);
		assertTrue(contributionsSummary.checkUserContribitionsExists(author));

		created = contributionsSummary.addUserContribution("markw",
		"Mark Wolfe");

		assertFalse(created);
		assertTrue(contributionsSummary.checkUserContribitionsExists(author));
		
		
	}

	@Test(expected = NoSuchElementException.class)
	public void testUserContributionNotFound() {
		ContributionsSummary contributionsSummary = new ContributionsSummary();

		contributionsSummary.userContributionByUserId("markw");
	}
}
