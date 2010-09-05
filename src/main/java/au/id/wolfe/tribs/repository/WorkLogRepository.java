package au.id.wolfe.tribs.repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * Contains any adhoc queries which directly access the data layer in JIRA.
 * 
 * I have done this as I know this is going to get BUSTED in the future so
 * better to have all this crap in one place.
 * 
 */
public interface WorkLogRepository {

    /**
     * Retrieve a list of work log ids
     */
    public List<Long> getWorkLogIdListForPeriod(Timestamp startTimestamp,
            Timestamp endTimestamp);
}
