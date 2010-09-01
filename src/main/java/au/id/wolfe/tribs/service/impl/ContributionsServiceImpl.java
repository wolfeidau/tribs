package au.id.wolfe.tribs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ofbiz.core.entity.EntityCondition;
import org.ofbiz.core.entity.EntityConditionList;
import org.ofbiz.core.entity.EntityExpr;
import org.ofbiz.core.entity.EntityOperator;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.id.wolfe.tribs.data.ContributionsSummary;
import au.id.wolfe.tribs.data.UserContribution;
import au.id.wolfe.tribs.service.ContributionsService;

import com.atlassian.core.util.collection.EasyList;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.worklog.OfBizWorklogStore;
import com.atlassian.jira.issue.worklog.Worklog;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.google.common.collect.Lists;

public class ContributionsServiceImpl implements ContributionsService {

    Logger logger = LoggerFactory.getLogger(ContributionsServiceImpl.class);

    ProjectManager projectManager;
    WorklogManager worklogManager;
    IssueManager issueManager;

    OfBizDelegator genericDelegator;

    public ContributionsServiceImpl(ProjectManager projectManager,
	    OfBizDelegator genericDelegator, IssueManager issueManager, WorklogManager worklogManager) {
	this.projectManager = projectManager;
	this.genericDelegator = genericDelegator;
	this.issueManager = issueManager;
	this.worklogManager = worklogManager;

    }

    public ContributionsSummary getAllUserContributions() {

	logger.info("Retrieving all contributions");

	ContributionsSummary contributionsSummary = new ContributionsSummary();

	List<Long> genericValues = getTimeWorked();

	for (Long gv : genericValues){
	    logger.info("id " + gv);
	    Worklog worklog = worklogManager.getById(gv);
	    contributionsSummary.addUserContribution(worklog.getAuthor(), worklog.getAuthorFullName());
	    
	    UserContribution userContribution = contributionsSummary.getUserContributionByUserId(worklog.getAuthor());
	    
	    Project project = worklog.getIssue().getProjectObject();
	    
	    userContribution.addOrUpdateProjectHours(project.getName(), project.getKey(), worklog.getTimeSpent());
	    
	}

	return contributionsSummary;
    }

    public ContributionsSummary getUserContributionsForPeriod(Date startDate,
	    Date endDate) {

	logger.info("Retrieving all contributions for given startDate "
		+ startDate + ", endDate " + endDate);
	
	ContributionsSummary contributionsSummary = new ContributionsSummary();

	getTimeWorked();

	return contributionsSummary;
    }

    /**
     * Retrieve a list of uid, timeworked, startdate, issueid
     */
    @SuppressWarnings("unchecked")
    private List<Long> getTimeWorked() {

	List<Long> worklogIdList = Lists.newLinkedList();
	
	List<EntityCondition> expressions = new ArrayList<EntityCondition>();

	expressions.add(new EntityExpr("startdate",
		EntityOperator.GREATER_THAN_EQUAL_TO, Timestamp
			.valueOf("2000-01-01 00:00:00")));
	expressions.add(new EntityExpr("startdate",
		EntityOperator.LESS_THAN_EQUAL_TO, Timestamp
			.valueOf("2011-01-01 00:00:00")));
	EntityCondition condition = new EntityConditionList(expressions,
		EntityOperator.AND);

	for ( GenericValue value : genericDelegator.findByCondition(
		OfBizWorklogStore.WORKLOG_ENTITY, condition,
		//EasyList.build("id", "timeworked", "startdate", "author", "authorfullname"),
		EasyList.build("id"),
		EasyList.build())){
	    worklogIdList.add(value.getLong("id"));
	    
	}
	
	return worklogIdList;
    }

}
