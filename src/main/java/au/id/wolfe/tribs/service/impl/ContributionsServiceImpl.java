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
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.id.wolfe.tribs.data.ContributionsReport;
import au.id.wolfe.tribs.data.UserContribution;
import au.id.wolfe.tribs.repository.WorkLogRepository;
import au.id.wolfe.tribs.service.ContributionsService;

import com.atlassian.jira.issue.worklog.Worklog;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.opensymphony.user.User;

/**
 * 
 * Service which provides access to work log information in the form of
 * aggregated reports from the JIRA API.
 * 
 */
public class ContributionsServiceImpl implements ContributionsService {

    private final Logger logger = LoggerFactory
            .getLogger(ContributionsServiceImpl.class);

    private final WorklogManager worklogManager;
    private final WorkLogRepository workLogRepository;
    private final JiraAuthenticationContext jiraAuthenticationContext;
    private final PermissionManager permissionManager;

    public ContributionsServiceImpl(WorkLogRepository workLogRepository,
            WorklogManager worklogManager,
            JiraAuthenticationContext jiraAuthenticationContext,
            PermissionManager permissionManager) {

        this.workLogRepository = workLogRepository;
        this.worklogManager = worklogManager;
        this.jiraAuthenticationContext = jiraAuthenticationContext;
        this.permissionManager = permissionManager;

    }

    public ContributionsReport getAllUserContributions() {

        logger.debug("Retrieving all contributions");

        return getUserContributionsForPeriod(
                Timestamp.valueOf("2000-01-01 00:00:00"),
                Timestamp.valueOf("2020-01-01 00:00:00"), null, null);

    }

    public ContributionsReport getUserContributionsForPeriod(Date startDate,
            Date endDate, String userid, String projectKey) {

        logger.debug("Retrieving all contributions for given startDate "
                + startDate + ", endDate " + endDate);

        User user = jiraAuthenticationContext.getUser();

        ContributionsReport contributionsReport = new ContributionsReport(
                "Success", 200);

        List<Long> genericValues = workLogRepository.getWorkLogIdListForPeriod(
                new Timestamp(startDate.getTime()),
                new Timestamp(endDate.getTime()));

        for (Long gv : genericValues) {
            Worklog worklog = worklogManager.getById(gv);

            Project project = worklog.getIssue().getProjectObject();

            if (checkOptionalField(worklog.getAuthor(), userid)
                    && checkOptionalField(project.getKey(), projectKey)) {

                UserContribution userContribution = contributionsReport
                        .addAndReturnUserContribution(worklog.getAuthor(),
                                worklog.getAuthorFullName());

                if (permissionManager.hasPermission(Permissions.BROWSE,
                        project, user)) {
                    userContribution.addOrUpdateProjectHours(project.getName(),
                            project.getKey(), worklog.getTimeSpent());
                }
            }

        }

        return contributionsReport;
    }

    private boolean checkOptionalField(String compareField, String optionalField) {
        return optionalField == null ? true : compareField
                .equals(optionalField);
    }

}
