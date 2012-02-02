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

package au.id.wolfe.tribs.resources;

import au.id.wolfe.tribs.data.ContributionsReport;
import au.id.wolfe.tribs.service.ContributionsService;
import au.id.wolfe.tribs.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContributionsResourceTest {

    @Mock
    ContributionsService contributionsService;

    @Test
    public void testGetAllUserContributions() throws Exception {

        ContributionsReport ContributionsReport = new ContributionsReport();

        ContributionsResource contributionsResource = getContributionsResource();

        when(contributionsService.getAllUserContributions()).thenReturn(
                ContributionsReport);

        ContributionsReport ContributionsReportResponse = contributionsResource
                .getAllUserContributions();

        assertEquals(ContributionsReport, ContributionsReportResponse);

        verify(contributionsService).getAllUserContributions();

    }

    @Test
    public void testGetUserContributionsForPeriod() throws Exception {

        Date startDate = DateUtils.parseISO8601Date("2010-01-01");
        Date endDate = DateUtils.parseISO8601Date("2010-02-01");

        ContributionsReport ContributionsReport = new ContributionsReport();

        ContributionsResource contributionsResource = getContributionsResource();

        when(
                contributionsService.getUserContributionsForPeriod(
                        eq(startDate), eq(endDate), any(String.class),
                        any(String.class))).thenReturn(ContributionsReport);

        ContributionsReport userContributionsResponse = contributionsResource
                .getUserContributionsForPeriod("2010-01-01", "2010-02-01",
                        null, null);

        assertEquals(ContributionsReport, userContributionsResponse);

        verify(contributionsService).getUserContributionsForPeriod(
                eq(startDate), eq(endDate), any(String.class),
                any(String.class));

    }

    @Test
    public void testGetUserContributionsForPeriodWithNullStartDate() throws Exception {

        Date startDate = DateUtils.parseISO8601Date("2000-01-01");
        Date endDate = DateUtils.parseISO8601Date("2010-02-01");

        ContributionsReport ContributionsReport = new ContributionsReport();

        ContributionsResource contributionsResource = getContributionsResource();

        when(
                contributionsService.getUserContributionsForPeriod(
                        eq(startDate), eq(endDate), any(String.class),
                        any(String.class))).thenReturn(ContributionsReport);

        ContributionsReport userContributionsResponse = contributionsResource
                .getUserContributionsForPeriod(null, "2010-02-01",
                        null, null);

        assertEquals(ContributionsReport, userContributionsResponse);

        verify(contributionsService).getUserContributionsForPeriod(
                eq(startDate), eq(endDate), any(String.class),
                any(String.class));

    }

    @Test
    public void testGetUserContributionsForPeriodWithNullEndDate()
            throws Exception {

        Date startDate = DateUtils.parseISO8601Date("2010-01-01");

        ContributionsReport ContributionsReport = new ContributionsReport();

        ContributionsResource contributionsResource = getContributionsResource();

        when(
                contributionsService.getUserContributionsForPeriod(
                        eq(startDate), any(Date.class), any(String.class), any(String.class))).thenReturn(
                ContributionsReport);

        ContributionsReport userContributionsResponse = contributionsResource
                .getUserContributionsForPeriod("2010-01-01", null, null, null);

        assertEquals(ContributionsReport, userContributionsResponse);

        verify(contributionsService).getUserContributionsForPeriod(
                eq(startDate), any(Date.class), any(String.class), any(String.class));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsTextValidationIsThrowForPeriodStartDate()
            throws Exception {

        ContributionsResource contributionsResource = getContributionsResource();

        contributionsResource.getUserContributionsForPeriod("", "2010-01-01",
                null, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsTextValidationIsThrowForPeriodEndDate()
            throws Exception {

        ContributionsResource contributionsResource = getContributionsResource();

        contributionsResource.getUserContributionsForPeriod("2010-01-01", "",
                null, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsDateParseIsThrowForPeriodStartDate()
            throws Exception {

        ContributionsResource contributionsResource = getContributionsResource();

        contributionsResource.getUserContributionsForPeriod("crappydate", "",
                null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsDateParseIsThrowForPeriodEndDate() throws Exception {

        ContributionsResource contributionsResource = getContributionsResource();

        contributionsResource.getUserContributionsForPeriod("2010-01-01",
                "crappydate", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsDateParseIsThrowForEmptyStringInUserid() throws Exception {

        ContributionsResource contributionsResource = getContributionsResource();

        contributionsResource.getUserContributionsForPeriod("2010-01-01",
                "2010-01-01", "", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsDateParseIsThrowForEmptyStringInProjectKey() throws Exception {

        ContributionsResource contributionsResource = getContributionsResource();

        contributionsResource.getUserContributionsForPeriod("2010-01-01",
                "2010-01-01", null, "");
    }

    private ContributionsResource getContributionsResource() {

        ContributionsResource contributionsResource = new ContributionsResource(
                contributionsService);

        return contributionsResource;
    }
}
