package br.com.adaptaconsultoria.amr.builder.object;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Learning implements Serializable {

	private String courseid;
	private String coursename;
	private Integer roleid;

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}
