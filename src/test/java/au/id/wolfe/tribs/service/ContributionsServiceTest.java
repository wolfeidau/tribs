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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.id.wolfe.tribs.data.ContributionsReport;
import au.id.wolfe.tribs.repository.WorkLogRepository;
import au.id.wolfe.tribs.service.impl.ContributionsServiceImpl;
import au.id.wolfe.tribs.utils.DateUtils;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.worklog.Worklog;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.google.common.collect.Lists;
import com.opensymphony.user.User;

@RunWith(PowerMockRunner.class)
@PrepareForTest(User.class)
public class ContributionsServiceTest {

    @Mock
    WorklogManager worklogManager;

    @Mock
    WorkLogRepository workLogRepository;
    
    @Mock
    JiraAuthenticationContext jiraAuthenticationContext;

    @Mock
    PermissionManager permissionManager;

    @Test
    public void testAllUserContributions() {

        ContributionsService contributionsService = getContributionsService();

        Date startDate = DateUtils.parseISO8601Date("2000-01-01");
        Date endDate = DateUtils.parseISO8601Date("2020-01-01");

        when(workLogRepository.getWorkLogIdListForPeriod(new Timestamp(
                startDate.getTime()), new Timestamp(endDate.getTime()))).thenReturn(Lists.newArrayList(1L));
        
        Worklog worklog = mock(Worklog.class);
        Issue issue = mock(Issue.class);
        Project project = mock(Project.class);
        User user = mock(User.class);
        
        when (worklog.getAuthor()).thenReturn("markw");
        when(worklog.getIssue()).thenReturn(issue);
        when(issue.getProjectObject()).thenReturn(project);
        when(project.getKey()).thenReturn("TEST");
        when(project.getName()).thenReturn("TEST");
        
        when(worklogManager.getById(1L)).thenReturn(worklog);
        when(jiraAuthenticationContext.getUser()).thenReturn(user);
        when(permissionManager.hasPermission(Permissions.BROWSE, project, user)).thenReturn(true);

        ContributionsReport userContributions = contributionsService
                .getAllUserContributions();

        assertNotNull(userContributions);
    }

    private ContributionsService getContributionsService() {
        return new ContributionsServiceImpl(workLogRepository,
                worklogManager, jiraAuthenticationContext, permissionManager);
    }

}
