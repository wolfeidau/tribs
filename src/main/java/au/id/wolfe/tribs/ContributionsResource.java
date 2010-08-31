package au.id.wolfe.tribs;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import au.id.wolfe.tribs.data.UserContribution;
import au.id.wolfe.tribs.service.ContributionsService;
import au.id.wolfe.tribs.service.impl.ContributionsServiceImpl;

import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

@Path("/contributions")
public class ContributionsResource {

    ContributionsService contributionsService;
    
    public void setContributionsService(ContributionsService contributionsService) {
        this.contributionsService = contributionsService;
    }
    
    public ContributionsResource(ProjectManager projectManager){
	contributionsService = new ContributionsServiceImpl(projectManager);
    }

    @GET
    @Path("/all")
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    public List<UserContribution> getAllUserContributions(){
	return contributionsService.getAllUserContributions();
    }

    @GET
    @Path("/period")
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    public List<UserContribution> getUserContributionsForPeriod(@PathParam("startDate") Date startDate,@PathParam("endDate") Date endDate) {
	return contributionsService.getUserContributionsForPeriod(startDate, endDate);
    }
}
