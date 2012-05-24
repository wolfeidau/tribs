package au.id.wolfe.tribs.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Map;

/**
 * Contains a status change.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusChange {

    private String statusId;
    private String statusDescription;

    public StatusChange() {
    }

    public StatusChange(Map.Entry<String, String> statusChangeEntry) {
        statusId = statusChangeEntry.getKey();
        statusDescription = statusChangeEntry.getValue();
    }



    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
