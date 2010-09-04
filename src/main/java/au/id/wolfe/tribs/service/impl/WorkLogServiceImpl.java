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

import java.util.Date;

import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.atlassian.jira.project.ProjectManager;

import au.id.wolfe.tribs.data.WorkLogReport;
import au.id.wolfe.tribs.service.WorkLogService;

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

    OfBizDelegator genericDelegator;

    public WorkLogServiceImpl(ProjectManager projectManager,
            OfBizDelegator genericDelegator, IssueManager issueManager,
            WorklogManager worklogManager) {
        this.projectManager = projectManager;
        this.genericDelegator = genericDelegator;
        this.issueManager = issueManager;
        this.worklogManager = worklogManager;
    }

    public WorkLogReport getUserProjectWorkLogsForPeriod(String userid,
            String projectKey, Date startDate, Date endDate) {

        return new WorkLogReport();
    }

}
