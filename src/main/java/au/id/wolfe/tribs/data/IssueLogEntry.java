package au.id.wolfe.tribs.data;


import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;
import java.util.List;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IssueLogEntry {

    private String issueKey;
    private String summary;
    private String description;
    private String priority;
    private String type;
    private Date createdDate;
    private Date resolutionDate;

    private List<HistoryEntry> historyEntryList = Lists.newArrayList();

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public List<HistoryEntry> getHistoryEntryList() {
        return historyEntryList;
    }

    public void setHistoryEntryList(List<HistoryEntry> historyEntryList) {
        this.historyEntryList = historyEntryList;
    }
}
