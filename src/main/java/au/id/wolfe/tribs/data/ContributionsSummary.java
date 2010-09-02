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
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * 
 * Data object which contains the status of a contribution report response.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ContributionsReport")
public class ContributionsSummary {

    private Integer code;
    private String message;

    @XmlElementWrapper(name = "UserContributionList")
    @XmlElement(name = "UserContribution")
    private List<UserContribution> userContributions = Lists.newLinkedList();

    public ContributionsSummary() {
    }

    public ContributionsSummary(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public List<UserContribution> getUserContributions() {
        return userContributions;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean checkUserContribitionsExists(final String userid) {
        Iterable<UserContribution> uclist = Iterables.filter(userContributions,
                new Predicate<UserContribution>() {
                    public boolean apply(UserContribution input) {
                        return input.getUserid().equals(userid);
                    }

                });

        return uclist.iterator().hasNext();
    }

    public boolean addUserContribution(String userid, String fullName) {
        if (checkUserContribitionsExists(userid)) {
            return false;
        } else {
            return userContributions
                    .add(new UserContribution(userid, fullName));
        }
    }

    /**
     * 
     * @param userid
     * @return
     * @throws java.util.NoSuchElementException
     *             if no user contribution is found.
     */
    public UserContribution userContributionByUserId(final String userid) {
        return Iterables.find(userContributions,
                new Predicate<UserContribution>() {
                    public boolean apply(UserContribution input) {
                        return input.getUserid() == userid;
                    }

                });
    }
}
