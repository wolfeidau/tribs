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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ContributionsSummaryTest {

    @Test
    public void testAddingNewUserContributions() {

        // When a new author is found in the work log we need to add a new user
        // contribution
        // to the object.
        // author, authorFullName,

        String author = "markw";

        boolean created;

        ContributionsSummary contributionsSummary = new ContributionsSummary();

        assertFalse(contributionsSummary.checkUserContribitionsExists(author));

        created = contributionsSummary.addUserContribution("markw",
                "Mark Wolfe");

        assertTrue(created);
        assertTrue(contributionsSummary.checkUserContribitionsExists(author));

        created = contributionsSummary.addUserContribution("markw",
                "Mark Wolfe");

        assertFalse(created);
        assertTrue(contributionsSummary.checkUserContribitionsExists(author));

    }

    @Test(expected = NoSuchElementException.class)
    public void testUserContributionNotFound() {
        ContributionsSummary contributionsSummary = new ContributionsSummary();

        contributionsSummary.userContributionByUserId("markw");
    }
}
