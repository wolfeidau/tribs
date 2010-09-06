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

import au.id.wolfe.tribs.data.ContributionsReport;
import au.id.wolfe.tribs.service.ContributionsService;
import au.id.wolfe.tribs.utils.DateUtils;

/**
 * 
 * Web resource which exposed in JIRA with methods relating to user
 * contributions.
 * 
 */
@Path("/contributions")
public class ContributionsResource {

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

    /**
     * 
     * This method retrieves a contribution report using the given parameters.
     * 
     * @param startDate
     *            - Date parameter to include data from, this is inclusive
     *            (Required).
     * @param endDate
     *            - Date parameter to include data to, this is exclusive
     *            (Optional).
     * @return ContributionsReport containing the filtered data.
     */
    @GET
    @Path("/period")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContributionsReport getUserContributionsForPeriod(
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        Date endDateValue;

        Assert.hasText(startDate, "startDate must not be empty");

        if (endDate == null) {
            endDateValue = new Date();
        } else {
            Assert.hasText(endDate);
            endDateValue = DateUtils.parseISO8601Date(endDate);
        }

        return contributionsService.getUserContributionsForPeriod(
                DateUtils.parseISO8601Date(startDate), endDateValue);

    }

}
