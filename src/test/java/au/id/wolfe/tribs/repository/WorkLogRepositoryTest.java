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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.ofbiz.core.entity.EntityCondition;
import org.ofbiz.core.entity.GenericValue;

import au.id.wolfe.tribs.repository.impl.WorkLogRepositoryImpl;
import au.id.wolfe.tribs.utils.DateUtils;

import com.atlassian.crowd.integration.util.Assert;
import com.atlassian.jira.issue.worklog.OfBizWorklogStore;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class WorkLogRepositoryTest {

    @Mock
    OfBizDelegator genericDelegator;

    @Test
    @SuppressWarnings("unchecked")
    public void testGetWorkLogIdListForPeriod() {

        WorkLogRepository workLogRepository = getWorkLogRepository();

        Date startDate = DateUtils.parseISO8601Date("2010-01-01");
        Date endDate = DateUtils.parseISO8601Date("2010-02-01");

        GenericValue gv = mock(GenericValue.class);

        when(gv.getLong("id")).thenReturn(1L);

        when(
                genericDelegator.findByCondition(
                        eq(OfBizWorklogStore.WORKLOG_ENTITY),
                        any(EntityCondition.class), any(List.class),
                        any(List.class))).thenReturn(Lists.newArrayList(gv));

        List<Long> workLogIdList = workLogRepository.getWorkLogIdListForPeriod(
                new Timestamp(startDate.getTime()),
                new Timestamp(endDate.getTime()));

        Assert.notNull(workLogIdList);

    }

    private WorkLogRepository getWorkLogRepository() {
        return new WorkLogRepositoryImpl(genericDelegator);
    }

}
