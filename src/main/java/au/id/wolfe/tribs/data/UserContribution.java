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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserContribution")
public class UserContribution {

	public String userid;
	public String fullName;
	
	@XmlElementWrapper(name="ProjectTimeSpentList")
	@XmlElement(name="ProjectTimeSpent")
	public List<ProjectTimeSpent> projectTimeSpentList = Lists.newLinkedList();

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
						return input.projectKey == projectKey;
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
