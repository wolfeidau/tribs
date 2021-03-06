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

import au.id.wolfe.tribs.fixtures.UserContributionFixture;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ContributionsReportTest {

    @Test
    public void testAddingNewUserContributionsWithNoDuplication() {

        // When a new author is found in the work log we need to add a new user
        // contribution, this will be located based on the author attribute.

        ContributionsReport contributionsReport = new ContributionsReport();

        UserContribution userContribution = contributionsReport
                .addAndReturnUserContribution(UserContributionFixture
                        .getUserContributionWithData().getUserid(),
                        UserContributionFixture.getUserContributionWithData()
                                .getFullName());

        assertEquals(UserContributionFixture.getUserContributionWithData(),
                userContribution);
        assertEquals(1, contributionsReport.getUserContributions().size());

        UserContribution anotherUserContribution = contributionsReport
                .addAndReturnUserContribution(UserContributionFixture
                        .getUserContributionWithData().getUserid(),
                        UserContributionFixture.getUserContributionWithData()
                                .getFullName());

        assertEquals(UserContributionFixture.getUserContributionWithData(),
                anotherUserContribution);
        assertEquals(1, contributionsReport.getUserContributions().size());

    }

    @Test(expected = NoSuchElementException.class)
    public void testUserContributionNotFound() {
        ContributionsReport ContributionsReport = new ContributionsReport();

        ContributionsReport.userContributionByUserId("markw");
    }
}
