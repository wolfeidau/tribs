package au.id.wolfe.tribs.resources;

import au.id.wolfe.tribs.data.IssueLogReport;
import au.id.wolfe.tribs.service.IssueLogService;
import au.id.wolfe.tribs.utils.DateUtils;
import org.springframework.util.Assert;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * This resource assembles a issue log report which contains a brief entry for each issue along with it's status
 * history.
 */
@Path("/issuelog")
public class IssueLogResource {

    private final IssueLogService issueLogService;

    public IssueLogResource(IssueLogService issueLogService) {
        this.issueLogService = issueLogService;
    }

    @GET
    @Path("/period")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public IssueLogReport getIssueLogForPeriod(
            @QueryParam("projectKey") String projectKey,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        Date startDateValue, endDateValue;

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

        return issueLogService.getProjectIssueLogsForPeriod(projectKey, startDateValue, endDateValue);
    }
}