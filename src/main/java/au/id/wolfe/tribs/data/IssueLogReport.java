package au.id.wolfe.tribs.data;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Data object which contains the status of a issue report response.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IssueLogReport {

    private List<IssueLogEntry> issueLogEntryList = Lists.newArrayList();

    public List<IssueLogEntry> getIssueLogEntryList() {
        return issueLogEntryList;
    }
}
