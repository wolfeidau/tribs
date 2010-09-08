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

package au.id.wolfe.tribs.resources;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.util.Assert;

import au.id.wolfe.tribs.data.WorkLogReport;
import au.id.wolfe.tribs.service.WorkLogService;
import au.id.wolfe.tribs.utils.DateUtils;

/**
 * 
 * Web resource which exposed in JIRA with methods relating to user work log
 * entries.
 * 
 */
@Path("/worklog")
public class WorkLogResource {

    WorkLogService workLogService;

    public WorkLogResource(WorkLogService workLogService) {
        this.workLogService = workLogService;
    }

    @GET
    @Path("/period")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public WorkLogReport getUserProjectWorkLogsForPeriod(
            @QueryParam("userid") String userid,
            @QueryParam("projectKey") String projectKey,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        Date startDateValue, endDateValue;

        if (userid != null) {
            Assert.hasText(userid, "userid must not be empty.");
        }

        if (projectKey != null) {
            Assert.hasText(projectKey, "projectKey must not be empty.");
        }

        if (startDate == null) {
            startDateValue = DateUtils.parseISO8601Date(DateUtils.DEFAULT_START_DATE);
        } else {
            Assert.hasText(startDate, "startDate must not be empty.");
            startDateValue = DateUtils.parseISO8601Date(startDate);
        }

        if (endDate == null) {
            endDateValue = new Date();
        } else {
            Assert.hasText(endDate, "endDate must not be empty.");
            endDateValue = DateUtils.parseISO8601Date(endDate);
        }

        return workLogService.getUserProjectWorkLogsForPeriod(userid,
                projectKey, startDateValue, endDateValue);
    }

}
