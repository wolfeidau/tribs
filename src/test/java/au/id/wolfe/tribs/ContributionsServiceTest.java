package au.id.wolfe.tribs;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import au.id.wolfe.tribs.data.ContributionsSummary;
import au.id.wolfe.tribs.service.ContributionsService;
import au.id.wolfe.tribs.service.impl.ContributionsServiceImpl;

import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.atlassian.jira.project.ProjectManager;

@RunWith(MockitoJUnitRunner.class)
public class ContributionsServiceTest {

	@Mock
	ProjectManager projectManager;

	@Mock
	IssueManager issueManager;

	@Mock
	WorklogManager worklogManager;

	@Mock
	OfBizDelegator genericDelegator;

	@Test
	public void testAllUserContributions() {

		//when(genericDelegator.findByCondition(eq(OfBizWorklogStore.WORKLOG_ENTITY), any(), eq(EasyList.build())))
		
		ContributionsService contributionsService = getContributionsService();

		ContributionsSummary userContributions = contributionsService
				.getAllUserContributions();

		assertNotNull(userContributions);
	}

	private ContributionsService getContributionsService() {
		return new ContributionsServiceImpl(projectManager, genericDelegator,
				issueManager, worklogManager);
	}

}
