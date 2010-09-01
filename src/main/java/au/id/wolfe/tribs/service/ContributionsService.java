package au.id.wolfe.tribs.service;

import java.util.Date;

import au.id.wolfe.tribs.data.ContributionsSummary;

public interface ContributionsService {

    ContributionsSummary getAllUserContributions();

    ContributionsSummary getUserContributionsForPeriod(Date startDate,
	    Date endDate);

}
