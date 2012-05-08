package au.id.wolfe.tribs.repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Contains any adhoc queries which directly access the data layer in JIRA.
 */
public interface IssueRepository {

    /**
     * Retrieve a list of work log ids
     */
    List<Long> getIssueIdListForProjectAndPeriod(Long projectId, Timestamp startTimestamp,
                                         Timestamp endTimestamp);

}
