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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * 
 * Data object which contains a list of projects which a user contributed time to.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserContribution")
public class UserContribution {

    private String userid;
    private String fullName;

    @XmlElementWrapper(name = "ProjectTimeSpentList")
    @XmlElement(name = "ProjectTimeSpent")
    private List<ProjectTimeSpent> projectTimeSpentList = Lists.newLinkedList();

    public UserContribution() {
    }

    public UserContribution(String userid, String fullName) {
        this.userid = userid;
        this.fullName = fullName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<ProjectTimeSpent> getProjectTimeSpentList() {
        return projectTimeSpentList;
    }

    public boolean addOrUpdateProjectHours(final String projectName,
            final String projectKey, final Long timeworked) {

        Iterable<ProjectTimeSpent> phlist = Iterables.filter(
                projectTimeSpentList, new Predicate<ProjectTimeSpent>() {
                    public boolean apply(ProjectTimeSpent input) {
                        return input.getProjectKey().equals(projectKey);
                    }

                });

        if (phlist.iterator().hasNext()) {
            // add the hours to the existing record.
            phlist.iterator().next().addHours(timeworked);
            return false;
        } else {
            projectTimeSpentList.add(new ProjectTimeSpent(projectName,
                    projectKey, timeworked));
            return true;
        }

    }

}
