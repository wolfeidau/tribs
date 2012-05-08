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

package au.id.wolfe.tribs.data;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class UserContributionTest {

    @Test
    public void testAddOfProjectTimeContributions() {

        boolean added;

        ProjectTimeSpent projectTimeSpent = new ProjectTimeSpent("STAR", "Star Project", 60l);

        UserContribution userContribution = new UserContribution("markw", "Mark Wolfe");

        added = userContribution.addOrUpdateProjectHours("STAR", "Star Project", 60l);

        assertTrue(added);

        assertEquals(true, userContribution.getProjectTimeSpentList().contains(projectTimeSpent));

        assertEquals(1, userContribution.getProjectTimeSpentList().size());

        added = userContribution.addOrUpdateProjectHours("STAR", "Star Project", 50l);

        assertFalse(added);

        projectTimeSpent.setTimespent(110l);

        assertTrue(userContribution.getProjectTimeSpentList().contains(projectTimeSpent));

        assertEquals(1, userContribution.getProjectTimeSpentList().size());

    }

}
