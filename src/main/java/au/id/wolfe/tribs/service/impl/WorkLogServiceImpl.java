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

import com.atlassian.jira.user.ApplicationUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.id.wolfe.tribs.data.WorkLogEntry;
import au.id.wolfe.tribs.data.WorkLogReport;
import au.id.wolfe.tribs.repository.WorkLogRepository;
import au.id.wolfe.tribs.service.WorkLogService;

import com.atlassian.jira.issue.worklog.Worklog;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;

/**
 * 
 * Service which provides access to work log detail information from the JIRA
 * API.
 * 
 */
public class WorkLogServiceImpl implements WorkLogService {

    private final Logger logger = LoggerFactory
            .getLogger(WorkLogServiceImpl.class);

    private final WorklogManager worklogManager;
    private final WorkLogRepository workLogRepository;
    private final JiraAuthenticationContext jiraAuthenticationContext;
    private final PermissionManager permissionManager;

    public WorkLogServiceImpl(WorkLogRepository workLogRepository,
            WorklogManager worklogManager,
            JiraAuthenticationContext jiraAuthenticationContext,
            PermissionManager permissionManager) {

        this.workLogRepository = workLogRepository;
        this.worklogManager = worklogManager;
        this.jiraAuthenticationContext = jiraAuthenticationContext;
        this.permissionManager = permissionManager;

    }

    public WorkLogReport getUserProjectWorkLogsForPeriod(String userid,
            String projectKey, Date startDate, Date endDate) {

        logger.debug("Retrieving all work logs for given startDate "
                + startDate + ", endDate " + endDate);

        WorkLogReport workLogReport = new WorkLogReport("Success", 200);

        ApplicationUser user = jiraAuthenticationContext.getUser();

        List<Long> workLogIdValues = workLogRepository
                .getWorkLogIdListForPeriod(new Timestamp(startDate.getTime()),
                        new Timestamp(endDate.getTime()));

        for (Long workLogId : workLogIdValues) {

            Worklog worklog = worklogManager.getById(workLogId);

            Project project = worklog.getIssue().getProjectObject();

            if (isWorkLogToBeIncluded(user, project, worklog, userid,
                    projectKey)) {

                workLogReport.getWorkLogEntryList().add(
                        new WorkLogEntry(worklog.getId(), worklog.getAuthor(),
                                worklog.getAuthorFullName(), worklog
                                        .getTimeSpent(), worklog.getIssue()
                                        .getKey(), worklog.getIssue()
                                        .getSummary(),
                                worklog.getCreated(), worklog.getUpdated()));
            }

        }

        return workLogReport;
    }

    private boolean isWorkLogToBeIncluded(ApplicationUser user, Project project,
            Worklog worklog, String userid, String projectKey) {

        return permissionManager.hasPermission(Permissions.BROWSE, project,
                user)
                && checkOptionalField(worklog.getAuthor(), userid)
                && checkOptionalField(worklog.getIssue().getProjectObject()
                        .getKey(), projectKey);
    }

    private boolean checkOptionalField(String compareField, String optionalField) {
        return optionalField == null ? true : compareField
                .equals(optionalField);
    }

}
