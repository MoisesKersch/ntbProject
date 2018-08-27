package br.com.adaptaconsultoria.amr.builder.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Learnings implements Serializable {

	private String userid;
	private List<Learning> courses = new ArrayList<Learning>();

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Learning> getCourses() {
		return courses;
	}

	public void setCourses(List<Learning> courses) {
		this.courses = courses;
	}

}
