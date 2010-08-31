package au.id.wolfe.tribs.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.id.wolfe.tribs.data.UserContribution;
import au.id.wolfe.tribs.service.ContributionsService;

import com.atlassian.jira.jql.builder.JqlQueryBuilder;
import com.atlassian.jira.project.ProjectManager;
import com.google.common.collect.Lists;

public class ContributionsServiceImpl implements ContributionsService {

    Logger logger = LoggerFactory.getLogger(ContributionsServiceImpl.class);

    ProjectManager projectManager;

    public ContributionsServiceImpl(ProjectManager projectManager) {
	this.projectManager = projectManager;
    }

    public List<UserContribution> getAllUserContributions() {

	logger.info("Retrieving all contributions");

	JqlQueryBuilder queryBuilder = JqlQueryBuilder.newBuilder();
	
	List<UserContribution> userContributions = Lists.newLinkedList();

	userContributions.add(new UserContribution("markw"));
	userContributions.add(new UserContribution("peter"));

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

}
