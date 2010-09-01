package au.id.wolfe.tribs;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import au.id.wolfe.tribs.data.ContributionsSummary;
import au.id.wolfe.tribs.service.ContributionsService;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

@Path("/contributions")
public class ContributionsResource {

    ContributionsService contributionsService;

    public ContributionsResource(final ContributionsService contributionsService) {
	this.contributionsService = contributionsService;
    }

    @GET
    @Path("/all")
    @AnonymousAllowed
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContributionsSummary getAllUserContributions() {
	return contributionsService.getAllUserContributions();

    }

    @GET
    @Path("/period")
    @AnonymousAllowed
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContributionsSummary getUserContributionsForPeriod(
	    @PathParam("startDate") Date startDate,
	    @PathParam("endDate") Date endDate) {
	return contributionsService.getUserContributionsForPeriod(startDate,
		endDate);
    }
}
