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

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * This data object contains provides flattened view of the JIRA work log items
 * attributes.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkLogEntry {

    private Long id;
    private String authorUserid;
    private String authorFullName;
    private Long timeSpent;
    private String issueKey;
    private String issueDescription;

    private Date created;
    private Date updated;

    public WorkLogEntry() {
    }

    public WorkLogEntry(Long id, String authorUserid, String authorFullName,
            Long timeSpent, String issueKey, String issueDescription,
            Date created, Date updated) {
        this.id = id;
        this.authorUserid = authorUserid;
        this.authorFullName = authorFullName;
        this.timeSpent = timeSpent;
        this.issueKey = issueKey;
        this.issueDescription = issueDescription;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorUserid() {
        return authorUserid;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
