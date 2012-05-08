package au.id.wolfe.tribs.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ContributionsReport")
public class Message {

    private String error;

    public Message() {
    }

    public Message(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
