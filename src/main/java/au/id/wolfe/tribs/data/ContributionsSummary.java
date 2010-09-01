package au.id.wolfe.tribs.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "contributions")
public class ContributionsSummary {

    List<UserContribution> userContributions = Lists.newLinkedList();

    public List<UserContribution> getUserContributions() {
	return userContributions;
    }

    public boolean checkUserContrubitionsExists(final String userid){
	Iterable<UserContribution> uclist = Iterables.filter(
		userContributions, new Predicate<UserContribution>() {
		    public boolean apply(UserContribution input) {
			return input.getUserid() == userid;
		    }

		});
	
	return uclist.iterator().hasNext();
    }

    public boolean addUserContribution(String userid, String fullName){
	if (checkUserContrubitionsExists(userid)){
	    return false;
	} else {
	    return userContributions.add(new UserContribution(userid, fullName));
	}
    }
    
    /**
     * 
     * @param userid
     * @return
     * @throws NoSuchElementException if no user contribution is found.
     */
    public UserContribution getUserContributionByUserId(final String userid){
	return Iterables.find(
		userContributions, new Predicate<UserContribution>() {
		    public boolean apply(UserContribution input) {
			return input.getUserid() == userid;
		    }

		});
    }
}
