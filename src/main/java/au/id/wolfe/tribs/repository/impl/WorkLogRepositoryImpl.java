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

package au.id.wolfe.tribs.repository.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.ofbiz.core.entity.EntityCondition;
import org.ofbiz.core.entity.EntityConditionList;
import org.ofbiz.core.entity.EntityExpr;
import org.ofbiz.core.entity.EntityOperator;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.core.util.collection.EasyList;
import com.atlassian.jira.issue.worklog.OfBizWorklogStore;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.google.common.collect.Lists;

import au.id.wolfe.tribs.repository.WorkLogRepository;
import au.id.wolfe.tribs.service.impl.ContributionsServiceImpl;

/**
 * 
 * Contains methods which extract work log related information from JIRA data
 * store.
 * 
 * I have done this as I know this is going to get BUSTED in the future so
 * better to have all this crap in one place.
 * 
 */
public class WorkLogRepositoryImpl implements WorkLogRepository {

    Logger logger = LoggerFactory.getLogger(ContributionsServiceImpl.class);

    OfBizDelegator genericDelegator;

    public WorkLogRepositoryImpl(OfBizDelegator genericDelegator) {
        this.genericDelegator = genericDelegator;
    }

    @SuppressWarnings("unchecked")
    public List<Long> getWorkLogIdListForPeriod(Timestamp startDate,
            Timestamp endDate) {

        logger.info("getWorkLogIdListForPeriod startDate " + startDate
                + ", endDate " + endDate);

        List<Long> worklogIdList = Lists.newLinkedList();

        List<EntityCondition> expressions = new ArrayList<EntityCondition>();

        expressions.add(new EntityExpr("startdate",
                EntityOperator.GREATER_THAN_EQUAL_TO, startDate));
        expressions.add(new EntityExpr("startdate", EntityOperator.LESS_THAN,
                endDate));

        EntityCondition condition = new EntityConditionList(expressions,
                EntityOperator.AND);

        for (GenericValue value : genericDelegator.findByCondition(
                OfBizWorklogStore.WORKLOG_ENTITY, condition,
                EasyList.build("id"), EasyList.build())) {
            worklogIdList.add(value.getLong("id"));

        }

        return worklogIdList;
    }

    @SuppressWarnings("unchecked")
    public List<Long> getUserWorkLogIdListForPeriod(Timestamp startDate,
            Timestamp endDate, String userid) {

        logger.info("getWorkLogIdListForPeriod startDate " + startDate
                + ", endDate " + endDate + ", userid " + userid);

        List<Long> worklogIdList = Lists.newLinkedList();

        List<EntityCondition> expressions = new ArrayList<EntityCondition>();

        expressions.add(new EntityExpr("startdate",
                EntityOperator.GREATER_THAN_EQUAL_TO, startDate));
        expressions.add(new EntityExpr("startdate", EntityOperator.LESS_THAN,
                endDate));

        expressions
                .add(new EntityExpr("author", EntityOperator.EQUALS, userid));

        EntityCondition condition = new EntityConditionList(expressions,
                EntityOperator.AND);

        for (GenericValue value : genericDelegator.findByCondition(
                OfBizWorklogStore.WORKLOG_ENTITY, condition,
                EasyList.build("id"), EasyList.build())) {
            worklogIdList.add(value.getLong("id"));

        }

        return worklogIdList;
    }
}
