package au.id.wolfe.tribs.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.atlassian.gzipfilter.org.apache.commons.lang.builder.EqualsBuilder;
import com.atlassian.gzipfilter.org.apache.commons.lang.builder.HashCodeBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectTimeSpent {

	String projectName;
	String projectKey;
	Long timespent = 0l;

	public ProjectTimeSpent(){
	}
	
	public ProjectTimeSpent(String projectName, String projectKey, Long timespent) {
		this.projectName = projectName;
		this.projectKey = projectKey;
		this.timespent = timespent;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public Long getTimespent() {
		return timespent;
	}

	public void setTimespent(Long timespent) {
		this.timespent = timespent;
	}

	public void addHours(Long timeworked) {
		this.timespent = this.timespent + timeworked;
		
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
