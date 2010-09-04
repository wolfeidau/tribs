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

package au.id.wolfe.tribs;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.time.DateUtils;

import au.id.wolfe.tribs.data.ContributionsReport;
import au.id.wolfe.tribs.service.ContributionsService;

/**
 * 
 * Web resource which exposed in JIRA with methods relating to user
 * contributions.
 * 
 */
@Path("/contributions")
public class ContributionsResource {

    static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    ContributionsService contributionsService;

    public ContributionsResource(final ContributionsService contributionsService) {
        this.contributionsService = contributionsService;
    }

    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContributionsReport getAllUserContributions() {
        return contributionsService.getAllUserContributions();
    }

    @GET
    @Path("/period")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContributionsReport getUserContributionsForPeriod(
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        Date startDateVal = null, endDateVal = null;
        try {
            startDateVal = DateUtils.parseDate(startDate,
                    new String[] { ISO8601_DATE_PATTERN });
            endDateVal = DateUtils.parseDate(endDate,
                    new String[] { ISO8601_DATE_PATTERN });
        } catch (ParseException e) {
            return new ContributionsReport(e.getMessage(), 500);
        }
        return contributionsService.getUserContributionsForPeriod(startDateVal,
                endDateVal);

    }
}
