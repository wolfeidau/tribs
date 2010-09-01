package au.id.wolfe.tribs;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import au.id.wolfe.tribs.data.ContributionsSummary;
import au.id.wolfe.tribs.service.ContributionsService;

@RunWith(MockitoJUnitRunner.class)
public class ContributionsResourceTest {

    String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    @Mock
    ContributionsService contributionsService;

    @Test
    public void testGetAllUserContributions() throws Exception {

	ContributionsSummary contributionsSummary = new ContributionsSummary();

	ContributionsResource contributionsResource = getContributionsResource();

	when(contributionsService.getAllUserContributions()).thenReturn(
		contributionsSummary);

	ContributionsSummary contributionsSummaryResponse = contributionsResource
		.getAllUserContributions();

	assertEquals(contributionsSummary, contributionsSummaryResponse);

	verify(contributionsService).getAllUserContributions();

    }

    @Test
    public void testGetUserContributionsForPeriod() throws Exception {

	Date startDate = DateUtils.parseDate("2010-01-01",
		new String[] { ISO8601_DATE_PATTERN });
	Date endDate = DateUtils.parseDate("2010-02-01",
		new String[] { ISO8601_DATE_PATTERN });

	ContributionsSummary userContributions = new ContributionsSummary();

	ContributionsResource contributionsResource = getContributionsResource();

	when(
		contributionsService.getUserContributionsForPeriod(
			eq(startDate), eq(endDate))).thenReturn(
		userContributions);

	ContributionsSummary userContributionsResponse = contributionsResource
		.getUserContributionsForPeriod(startDate, endDate);

	assertEquals(userContributions, userContributionsResponse);

	verify(contributionsService).getUserContributionsForPeriod(
		eq(startDate), eq(endDate));

    }

    private ContributionsResource getContributionsResource() {

	ContributionsResource contributionsResource = new ContributionsResource(
		contributionsService);

	return contributionsResource;
    }
}
