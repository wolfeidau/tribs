package au.id.wolfe.tribs.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Represents a JIRA user.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    private String displayName;
    private String emailAddress;

    public User() {
    }

    public User(String displayName, String emailAddress) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
