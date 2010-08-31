package au.id.wolfe.tribs.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userContribution")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserContribution {

    public String userid;

    public UserContribution() {
    }

    public UserContribution(String userid) {
	this.userid = userid;
    }

    public String getUserid() {
	return userid;
    }

    public void setUserid(String userid) {
	this.userid = userid;
    }

}
