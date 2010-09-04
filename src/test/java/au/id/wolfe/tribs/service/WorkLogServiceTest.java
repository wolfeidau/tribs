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

package au.id.wolfe.tribs.service;

import static junit.framework.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mock;

import au.id.wolfe.tribs.data.WorkLogReport;
import au.id.wolfe.tribs.service.impl.WorkLogServiceImpl;

import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.atlassian.jira.project.ProjectManager;

public class WorkLogServiceTest {

    @Mock
    ProjectManager projectManager;

    @Mock
    IssueManager issueManager;

    @Mock
    WorklogManager worklogManager;

    @Mock
    OfBizDelegator genericDelegator;

    @Test
    public void testGetUserProjectWorkLogsForPeriod() throws Exception {
        WorkLogService workLogService = getWorkLogService();

        WorkLogReport workLogReport = workLogService
                .getUserProjectWorkLogsForPeriod("markw", "STAR", new Date(),
                        new Date());

        assertNotNull(workLogReport);
    }

    private WorkLogService getWorkLogService() {
        return new WorkLogServiceImpl(projectManager, genericDelegator,
                issueManager, worklogManager);
    }
}
