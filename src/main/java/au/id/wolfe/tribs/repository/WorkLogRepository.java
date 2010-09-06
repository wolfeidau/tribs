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
    List<Long> getWorkLogIdListForPeriod(Timestamp startTimestamp,
            Timestamp endTimestamp);

    /**
     *  Retrieve a list of work log ids for a particular user.
     */
    public List<Long> getUserWorkLogIdListForPeriod(Timestamp startDate,
            Timestamp endDate, String userid);
}
