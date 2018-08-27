package br.com.adaptaconsultoria.amr.builder.object;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Course implements Serializable {

	private String courseid;
	private String fullname;
	private String shortname;
	private Integer numsections;
	private String summary;

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public Integer getNumsections() {
		return numsections;
	}

	public void setNumsections(Integer numsections) {
		this.numsections = numsections;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
