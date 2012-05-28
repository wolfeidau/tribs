package au.id.wolfe.tribs.service;

import au.id.wolfe.tribs.data.IssueLogEntry;
import au.id.wolfe.tribs.data.IssueLogReport;
import au.id.wolfe.tribs.repository.IssueRepository;
import au.id.wolfe.tribs.service.impl.IssueLogServiceImpl;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryItem;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.priority.Priority;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class IssueLogServiceImplTest {

    @Mock
    IssueRepository issueRepository;

    @Mock
    private IssueManager issueManager;

    @Mock
    private ProjectManager projectManager;

    @Mock
    private IssueLogService issueLogService;

    @Mock
    private ChangeHistoryManager changeHistoryManager;

    @Before
    public void setUp() throws Exception {

        issueLogService = new IssueLogServiceImpl(issueRepository, issueManager, projectManager, changeHistoryManager);

    }

    @Test
    public void testGetProjectIssueLogsForPeriod() throws Exception {

        Project project = mock(Project.class);

        when(project.getId()).thenReturn(123l);

        when(projectManager.getProjectObjByKey("TEST")).thenReturn(project);

        List<Long> issueIdList = populateIssues(10);
        //List<Long> issueIdList = Lists.newArrayList(1l, 2l, 3l);


        when(issueRepository.getIssueIdListForProjectAndPeriod(any(Long.class), any(Timestamp.class), any(Timestamp.class))).thenReturn(issueIdList);

        IssueLogReport issueLogReport = issueLogService.getProjectIssueLogsForPeriod("TEST", new Date(), DateUtils.addDays(new Date(), 30));

        assertEquals(10, issueLogReport.getIssueLogEntryList().size());

        // todo pull apart the model
        for (IssueLogEntry issueLogEntry : issueLogReport.getIssueLogEntryList()){
            assertNotNull(issueLogEntry.getSummary());
            assertNotNull(issueLogEntry.getDescription());
        }


        assertNotNull(issueLogReport);
    }

    private List<Long> populateIssues(int count) {

        List<Long> issueIdList = Lists.newArrayList();

        for (int i = 0; i < count; i++){
            MutableIssue issue = mock(MutableIssue.class);

            IssueType issueType = mock(IssueType.class);
            when(issueType.getName()).thenReturn("Bug");
            when(issue.getIssueTypeObject()).thenReturn(issueType);

            Priority priority = mock(Priority.class);
            when(priority.getName()).thenReturn("Major");
            when(issue.getPriorityObject()).thenReturn(priority);

            User assignedUser = newUser("Mark Wolfe", "mark.wolfe@hsd.com.au");
            User reportingUser = newUser("Peter Sawyer", "peter@hsd.com.au");

            when(issue.getAssigneeUser()).thenReturn(assignedUser);
            when(issue.getReporterUser()).thenReturn(reportingUser);

            when(issueManager.getIssueObject((long) i)).thenReturn(issue);

            when(issue.getKey()).thenReturn(String.format("TEST-%d", i));
            when(issue.getSummary()).thenReturn(String.format("Summary if issue i of %d", i));
            when(issue.getDescription()).thenReturn(String.format("Some really really really big issue with an i of %d", i));
            when(issue.getCreated()).thenReturn(new Timestamp(System.currentTimeMillis()));

            List<ChangeHistoryItem> changeHistoryItems = Lists.newLinkedList();

            changeHistoryItems.add(newChangeHistoryItem("status", "1", "Open", "3", "Resolved"));

            when(changeHistoryManager.getAllChangeItems(any(Issue.class))).thenReturn(changeHistoryItems);

            issueIdList.add((long) i);

        }


        return issueIdList;
    }

    private User newUser(String displayName, String emailAddress) {
        User user = mock(User.class);
        when(user.getDisplayName()).thenReturn(displayName);
        when(user.getEmailAddress()).thenReturn(emailAddress);
        return user;
    }


    private ChangeHistoryItem newChangeHistoryItem(String field, String fromKey, String fromDescription, String toKey, String toDescription){

        ChangeHistoryItem changeHistoryItem = mock(ChangeHistoryItem.class);

        when(changeHistoryItem.getField()).thenReturn(field);

        Map<String, String> tos = Maps.newHashMap();
        tos.put(toKey, toDescription);

        Map<String, String> froms = Maps.newHashMap();
        froms.put(fromKey, fromDescription);

        when(changeHistoryItem.getTos()).thenReturn(tos);
        when(changeHistoryItem.getFroms()).thenReturn(froms);

        return changeHistoryItem;
    }
}
