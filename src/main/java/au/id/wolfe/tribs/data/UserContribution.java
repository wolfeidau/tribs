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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.common.collect.Lists;

/**
 * 
 * Data object which contains a list of projects which a user contributed time
 * to.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class UserContribution {

    private String userid;
    private String fullName;

    private List<ProjectTimeSpent> projectTimeSpentList = Lists.newArrayList();

    public UserContribution() {
    }

    public UserContribution(String userid, String fullName) {
        this.userid = userid;
        this.fullName = fullName;
    }

    public String getUserid() {
        return userid;
    }

    public String getFullName() {
        return fullName;
    }

    public List<ProjectTimeSpent> getProjectTimeSpentList() {
        return projectTimeSpentList;
    }

    public boolean addOrUpdateProjectHours(String projectName,
            String projectKey, Long timeworked) {

        for (ProjectTimeSpent pts : projectTimeSpentList) {
            if (pts.getProjectKey().equals(projectKey)) {
                pts.addHours(timeworked);
                return false;
            }
        }

        return projectTimeSpentList.add(new ProjectTimeSpent(projectName,
                projectKey, timeworked));

    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj,
                new String[] { "projectTimeSpentList" });
    }

}
