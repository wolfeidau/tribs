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

import au.id.wolfe.tribs.data.WorkLogEntry;
import au.id.wolfe.tribs.data.WorkLogReport;
import au.id.wolfe.tribs.repository.WorkLogRepository;
import au.id.wolfe.tribs.service.WorkLogService;

import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.worklog.Worklog;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.project.ProjectManager;

/**
 * 
 * Service which provides access to work log detail information from the JIRA
 * API.
 * 
 */
public class WorkLogServiceImpl implements WorkLogService {

    ProjectManager projectManager;
    WorklogManager worklogManager;
    IssueManager issueManager;

    WorkLogRepository workLogRepository;

    public WorkLogServiceImpl(ProjectManager projectManager,
            WorkLogRepository workLogRepository, IssueManager issueManager,
            WorklogManager worklogManager) {
        this.projectManager = projectManager;
        this.workLogRepository = workLogRepository;
        this.issueManager = issueManager;
        this.worklogManager = worklogManager;
    }

    public WorkLogReport getUserProjectWorkLogsForPeriod(String userid,
            String projectKey, Date startDate, Date endDate) {

        WorkLogReport workLogReport = new WorkLogReport("Success", 200);

        List<Long> workLogIdValues = workLogRepository
                .getWorkLogIdListForPeriod(new Timestamp(startDate.getTime()),
                        new Timestamp(endDate.getTime()));

        for (Long workLogId : workLogIdValues) {

            Worklog worklog = worklogManager.getById(workLogId);

            if (worklog.getAuthor().equals(userid)
                    && worklog.getIssue().getProjectObject().getKey()
                            .equals(projectKey)) {
                
                workLogReport.getWorkLogEntryList().add(
                        new WorkLogEntry(worklog.getId(), worklog.getAuthor(),
                                worklog.getAuthorFullName(), worklog
                                        .getTimeSpent(), worklog.getIssue()
                                        .getKey(), worklog.getIssue()
                                        .getDescription(),
                                worklog.getCreated(), worklog.getUpdated()));
            }

        }

        return workLogReport;
    }

}
