/**
 * Copyright (C) 2010 Mark Wolfe <mark.wolfe@wolfe.id.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import au.id.wolfe.tribs.data.ContributionsReport;
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

/**
 * 
 * Service which provides access to work log information in the form of
 * aggregated reports from the JIRA API.
 * 
 */
public class ContributionsServiceImpl implements ContributionsService {

    Logger logger = LoggerFactory.getLogger(ContributionsServiceImpl.class);

    ProjectManager projectManager;
    WorklogManager worklogManager;
    IssueManager issueManager;

    OfBizDelegator genericDelegator;

    public ContributionsServiceImpl(ProjectManager projectManager,
            OfBizDelegator genericDelegator, IssueManager issueManager,
            WorklogManager worklogManager) {
        this.projectManager = projectManager;
        this.genericDelegator = genericDelegator;
        this.issueManager = issueManager;
        this.worklogManager = worklogManager;

    }

    public ContributionsReport getAllUserContributions() {

        logger.error("Retrieving all contributions");

        return getUserContributionsForPeriod(
                Timestamp.valueOf("2000-01-01 00:00:00"),
                Timestamp.valueOf("2020-01-01 00:00:00"));

    }

    public ContributionsReport getUserContributionsForPeriod(Date startDate,
            Date endDate) {

        logger.error("Retrieving all contributions for given startDate "
                + startDate + ", endDate " + endDate);

        ContributionsReport contributionsReport = new ContributionsReport();

        List<Long> genericValues = getTimeWorked(
                new Timestamp(startDate.getTime()),
                new Timestamp(endDate.getTime()));

        for (Long gv : genericValues) {
            Worklog worklog = worklogManager.getById(gv);

            UserContribution userContribution = contributionsReport
                    .addAndReturnUserContribution(worklog.getAuthor(),
                            worklog.getAuthorFullName());

            Project project = worklog.getIssue().getProjectObject();

            userContribution.addOrUpdateProjectHours(project.getName(),
                    project.getKey(), worklog.getTimeSpent());

        }

        contributionsReport.setCode(200);
        contributionsReport.setMessage("Success");

        return contributionsReport;
    }

    /**
     * Retrieve a list of work log ids
     */
    @SuppressWarnings("unchecked")
    private List<Long> getTimeWorked(Timestamp startDate, Timestamp endDate) {

        logger.error("getTimeWorked startDate " + startDate + ", endDate "
                + endDate);

        List<Long> worklogIdList = Lists.newLinkedList();

        List<EntityCondition> expressions = new ArrayList<EntityCondition>();

        expressions.add(new EntityExpr("startdate",
                EntityOperator.GREATER_THAN_EQUAL_TO, startDate));
        expressions.add(new EntityExpr("startdate", EntityOperator.LESS_THAN,
                endDate));

        EntityCondition condition = new EntityConditionList(expressions,
                EntityOperator.AND);

        for (GenericValue value : genericDelegator.findByCondition(
                OfBizWorklogStore.WORKLOG_ENTITY, condition,
                EasyList.build("id"), EasyList.build())) {
            worklogIdList.add(value.getLong("id"));

        }

        return worklogIdList;
    }

}
