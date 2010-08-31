package au.id.wolfe.tribs.service;

import java.util.Date;
import java.util.List;

import au.id.wolfe.tribs.data.UserContribution;

public interface ContributionsService {

    List<UserContribution> getAllUserContributions();

    List<UserContribution> getUserContributionsForPeriod(Date startDate,
	    Date endDate);

}
