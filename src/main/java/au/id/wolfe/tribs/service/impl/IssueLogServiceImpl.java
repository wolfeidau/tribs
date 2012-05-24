package au.id.wolfe.tribs.service.impl;

import au.id.wolfe.tribs.data.*;
import au.id.wolfe.tribs.repository.IssueRepository;
import au.id.wolfe.tribs.service.IssueLogService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.changehistory.ChangeHistoryItem;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service which provides access to issue log detail information from the JIRA API.
 */
public class IssueLogServiceImpl implements IssueLogService {

    private final Logger logger = LoggerFactory
            .getLogger(IssueLogServiceImpl.class);

    private final IssueRepository issueRepository;
    private final IssueManager issueManager;
    private final ProjectManager projectManager;
    private final ChangeHistoryManager changeHistoryManager;


    public IssueLogServiceImpl(IssueRepository issueRepository, IssueManager issueManager, ProjectManager projectManager, ChangeHistoryManager changeHistoryManager) {
        this.issueRepository = issueRepository;
        this.issueManager = issueManager;
        this.projectManager = projectManager;
        this.changeHistoryManager = changeHistoryManager;
    }

    @Override
    public IssueLogReport getProjectIssueLogsForPeriod(String projectKey, Date startDate, Date endDate) {

        IssueLogReport issueLogReport = new IssueLogReport();

        Project project = projectManager.getProjectObjByKey(projectKey);

        Assert.notNull(project, "Project for given key was not found.");

        Collection<Long> issueIdList = issueRepository.getIssueIdListForProjectAndPeriod(project.getId(), dateToTimestamp(startDate), dateToTimestamp(endDate));

        for (Long issueId : issueIdList) {

            // issue data
            Issue issue = issueManager.getIssueObject(issueId);

            // history data
            List<ChangeHistoryItem> changeHistoryList = changeHistoryManager.getAllChangeItems(issue);

            issueLogReport.getIssueLogEntryList().add(buildIssueLogEntry(issue, changeHistoryList));

        }


        return issueLogReport;
    }

    private IssueLogEntry buildIssueLogEntry(Issue issue, List<ChangeHistoryItem> changeHistoryList) {

        IssueLogEntry issueLogEntry = new IssueLogEntry();

        issueLogEntry.setCreatedDate(issue.getCreated());
        issueLogEntry.setResolutionDate(issue.getResolutionDate());
        issueLogEntry.setSummary(issue.getSummary());
        issueLogEntry.setDescription(issue.getDescription());

        issueLogEntry.setPriority(issue.getPriorityObject().getName());
        issueLogEntry.setType(issue.getIssueTypeObject().getName());

        if (issue.getAssigneeUser() != null){
            // populate assigned user
            issueLogEntry.setAssignedUser(new User(issue.getAssigneeUser().getDisplayName(), issue.getAssigneeUser().getEmailAddress()));
        }

        if (issue.getReporterUser() != null) {
            // populate reporter user
            issueLogEntry.setReporterUser(new User(issue.getReporterUser().getDisplayName(), issue.getReporterUser().getEmailAddress()));
        }

        for (ChangeHistoryItem changeHistoryItem : changeHistoryList) {

            if (changeHistoryItem.getField().equals("status")) {
                HistoryEntry historyEntry = new HistoryEntry();

                historyEntry.setField(changeHistoryItem.getField());
                for (Map.Entry<String, String> changeFromEntry: changeHistoryItem.getFroms().entrySet()){
                    historyEntry.getFroms().add( new StatusChange(changeFromEntry));
                }
                for (Map.Entry<String, String> changeToEntry: changeHistoryItem.getTos().entrySet()){
                    historyEntry.getTos().add(new StatusChange(changeToEntry));
                }

                historyEntry.setCreatedDate(changeHistoryItem.getCreated());

                issueLogEntry.getHistoryEntryList().add(historyEntry);
            }

        }

        return issueLogEntry;
    }

    private Timestamp dateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
}
