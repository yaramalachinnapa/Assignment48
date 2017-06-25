package com.balazsholczer.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@NotNull(message = "Must specify first name!")
	@Column(name = "first_name")
	private String firstName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	@NotNull(message="University must be filled!")
	private University university;

	@NotNull(message = "Must specify last name!")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "Must specify age!")
	@Min(value = 0, message = "Can not be less than 0")
	@Max(value = 100, message = "Can not be greater than 100")
	@Column(name = "age")
	private Integer age;

	@NotNull(message = "Must specify gender!")
	@Column(name = "gender")
	private String gender;

	public Student() {

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return this.firstName + "-" + this.lastName + "-" + this.age;
	}
}
