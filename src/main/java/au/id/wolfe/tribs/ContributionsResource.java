package au.id.wolfe.tribs;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.time.DateUtils;

import au.id.wolfe.tribs.data.ContributionsSummary;
import au.id.wolfe.tribs.service.ContributionsService;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

@Path("/contributions")
public class ContributionsResource {

	String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

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
			@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate) {

		Date startDateVal = null, endDateVal = null;
		try {
			startDateVal = DateUtils.parseDate(startDate,
					new String[] { ISO8601_DATE_PATTERN });
			endDateVal = DateUtils.parseDate(endDate,
					new String[] { ISO8601_DATE_PATTERN });
		} catch (ParseException e) {
			return new ContributionsSummary(e.getMessage(), 500);
		}
		return contributionsService.getUserContributionsForPeriod(startDateVal,
				endDateVal);

	}
}
