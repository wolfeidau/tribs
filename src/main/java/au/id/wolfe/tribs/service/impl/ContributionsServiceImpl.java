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

import au.id.wolfe.tribs.data.ProjectTimeSpent;
import au.id.wolfe.tribs.data.UserContribution;
import au.id.wolfe.tribs.service.ContributionsService;

import com.atlassian.core.util.collection.EasyList;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.worklog.OfBizWorklogStore;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.google.common.collect.Lists;

public class ContributionsServiceImpl implements ContributionsService {

	Logger logger = LoggerFactory.getLogger(ContributionsServiceImpl.class);

	ProjectManager projectManager;
	IssueManager issueManager;

	OfBizDelegator genericDelegator;

	public ContributionsServiceImpl(ProjectManager projectManager,
			OfBizDelegator genericDelegator, IssueManager issueManager) {
		this.projectManager = projectManager;
		this.genericDelegator = genericDelegator;
		this.issueManager = issueManager;

	}

	public List<UserContribution> getAllUserContributions() {

		logger.info("Retrieving all contributions");

		List<UserContribution> userContributions = Lists.newLinkedList();

		userContributions.add(new UserContribution("markw"));
		userContributions.add(new UserContribution("peter"));

		List<Project> projects = projectManager.getProjectObjects();

		for (UserContribution userContribution : userContributions) {
			for (Project project : projects) {
				ProjectTimeSpent projectTimeSpent = new ProjectTimeSpent();
				projectTimeSpent.setProjectName(project.getName());
				projectTimeSpent.setProjectKey(project.getKey());
				userContribution.getProjectTimeSpentList().add(projectTimeSpent);
			}
		}

		return userContributions;
	}

	public List<UserContribution> getUserContributionsForPeriod(Date startDate,
			Date endDate) {

		logger.info("Retrieving all contributions for given startDate "
				+ startDate + ", endDate " + endDate);

		List<UserContribution> userContributions = Lists.newLinkedList();

		userContributions.add(new UserContribution("markw"));
		userContributions.add(new UserContribution("peter"));

		return userContributions;
	}

	/**
	 * Retrieve a list of uid, timeworked, startdate, issueid
	 */
	private void getTimeWorked() {

		List<EntityCondition> expressions = new ArrayList<EntityCondition>();

		expressions.add(new EntityExpr("startdate",
				EntityOperator.GREATER_THAN_EQUAL_TO, Timestamp
						.valueOf("2000-01-01 00:00:00")));
		expressions.add(new EntityExpr("startdate",
				EntityOperator.LESS_THAN_EQUAL_TO, Timestamp
						.valueOf("2011-01-01 00:00:00")));
		EntityCondition condition = new EntityConditionList(expressions,
				EntityOperator.AND);

		for (GenericValue gv : genericDelegator.findByCondition(
				OfBizWorklogStore.WORKLOG_ENTITY, condition,
				EasyList.build("timeworked", "startdate", "author"), EasyList.build())) {
			// author
			// timeworked
			//data.addTest(gv.getLong("timeworked"));
			// data.addString();
			//data.addString(gv.getTimestamp("startdate").toString());
			
		}
	}

}
