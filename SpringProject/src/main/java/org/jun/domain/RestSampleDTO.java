package org.jun.domain;

public class RestSampleDTO {
	int mno;
	String firstName;
	String lastName;


	public RestSampleDTO(int mno, String firstName,String lastName) {
		this.mno=mno;
		this.firstName=firstName;
		this.lastName=lastName;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "RestSampleDTO [mno=" + mno + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
