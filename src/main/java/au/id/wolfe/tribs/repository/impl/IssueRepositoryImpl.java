package au.id.wolfe.tribs.repository.impl;

import au.id.wolfe.tribs.repository.IssueRepository;
import au.id.wolfe.tribs.service.impl.ContributionsServiceImpl;
import com.atlassian.core.util.collection.EasyList;
import com.atlassian.jira.issue.worklog.OfBizWorklogStore;
import com.atlassian.jira.ofbiz.OfBizDelegator;
import com.google.common.collect.Lists;
import org.ofbiz.core.entity.EntityCondition;
import org.ofbiz.core.entity.EntityConditionList;
import org.ofbiz.core.entity.EntityExpr;
import org.ofbiz.core.entity.EntityOperator;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains any adhoc queries which directly access the data layer in JIRA.
 */
public class IssueRepositoryImpl implements IssueRepository {

    Logger logger = LoggerFactory.getLogger(IssueRepositoryImpl.class);

    OfBizDelegator genericDelegator;

    public IssueRepositoryImpl(OfBizDelegator genericDelegator) {
        this.genericDelegator = genericDelegator;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Long> getIssueIdListForProjectAndPeriod(Long projectId, Timestamp startDate,
                                                        Timestamp endDate) {

        List<Long> issueIdList = Lists.newLinkedList();

        List<EntityCondition> expressions = Lists.newArrayList();

        expressions.add(new EntityExpr("created",
                EntityOperator.GREATER_THAN_EQUAL_TO, startDate));
        expressions.add(new EntityExpr("created", EntityOperator.LESS_THAN,
                endDate));
        expressions.add(new EntityExpr("project", EntityOperator.EQUALS, projectId));

        EntityCondition condition = new EntityConditionList(expressions,
                EntityOperator.AND);

        for (GenericValue value : genericDelegator.findByCondition(
                "Issue", condition,
                EasyList.build("id"), EasyList.build())) {
            issueIdList.add(value.getLong("id"));

        }

        return issueIdList;
    }
}
