package au.id.wolfe.tribs.data;

import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ContributionsReport")
public class ContributionsSummary {

	private Integer code;
	private String message;

	@XmlElementWrapper(name="UserContributionList")
	@XmlElement(name="UserContribution")
	List<UserContribution> userContributions = Lists.newLinkedList();

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
	 * @throws NoSuchElementException
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
