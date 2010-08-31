package au.id.wolfe.tribs;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static junit.framework.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import au.id.wolfe.tribs.data.UserContribution;
import au.id.wolfe.tribs.service.ContributionsService;

import com.atlassian.jira.project.ProjectManager;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class ContributionsResourceTest {

    String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    @Mock
    ContributionsService contributionsService;
    
    @Mock 
    ProjectManager projectManager;

    @Test
    public void testGetAllUserContributions() throws Exception {

	List<UserContribution> userContributions = Lists.newLinkedList();

	ContributionsResource contributionsResource = getContributionsResource();

	when(contributionsService.getAllUserContributions()).thenReturn(
		userContributions);

	List<UserContribution> userContributionsResponse = contributionsResource
		.getAllUserContributions();

	assertEquals(userContributions, userContributionsResponse);

	verify(contributionsService).getAllUserContributions();

    }

    @Test
    public void testGetUserContributionsForPeriod() throws Exception {

	Date startDate = DateUtils.parseDate("2010-01-01",
		new String[] { ISO8601_DATE_PATTERN });
	Date endDate = DateUtils.parseDate("2010-02-01",
		new String[] { ISO8601_DATE_PATTERN });

	List<UserContribution> userContributions = Lists.newLinkedList();

	ContributionsResource contributionsResource = getContributionsResource();

	when(
		contributionsService.getUserContributionsForPeriod(
			eq(startDate), eq(endDate))).thenReturn(
		userContributions);

	List<UserContribution> userContributionsResponse = contributionsResource
		.getUserContributionsForPeriod(startDate, endDate);

	assertEquals(userContributions, userContributionsResponse);

	verify(contributionsService).getUserContributionsForPeriod(
		eq(startDate), eq(endDate));

    }

    private ContributionsResource getContributionsResource() {
	ContributionsResource contributionsResource = new ContributionsResource(projectManager);

	contributionsResource.setContributionsService(contributionsService);

	return contributionsResource;
    }
}
