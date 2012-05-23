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

import au.id.wolfe.tribs.data.IssueLogReport;

import java.util.Date;

/**
 * Issue log builds a summary of issues based on the given params.
 */
public interface IssueLogService {

    /**
     * Given a valid project key this will search and return a list of issues with thier
     * associated status change history, reporter and assigned users.
     *
     * @param projectKey The project key to filter by.
     * @param startDate The start date.
     * @param endDate The end date.
     * @return An issue log report.
     */
    IssueLogReport getProjectIssueLogsForPeriod(String projectKey, Date startDate, Date endDate);

}
