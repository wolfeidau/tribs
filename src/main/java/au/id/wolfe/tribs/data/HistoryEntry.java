package au.id.wolfe.tribs.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;
import java.util.Map;

/**
 * Basic history of an issue.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryEntry {

    private String field;
    private Map<String, String> froms;
    private Map<String, String> tos;
    private Date createdDate;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Map<String, String> getFroms() {
        return froms;
    }

    public void setFroms(Map<String, String> froms) {
        this.froms = froms;
    }

    public Map<String, String> getTos() {
        return tos;
    }

    public void setTos(Map<String, String> tos) {
        this.tos = tos;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
