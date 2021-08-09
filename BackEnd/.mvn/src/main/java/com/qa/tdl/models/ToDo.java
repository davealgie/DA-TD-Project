package com.qa.tdl.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "todo")
public class ToDo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotNull
	@Size(min = 1, max = 120)
	private String name;
	
	@Column(name = "priority")
	private Integer priority;
	
	public ToDo(Integer id, @NotNull @Size(min = 1, max = 120) String name, Integer priority) {
		this.id = id;
		this.name = name;
		this.priority = priority;
	}
	public ToDo(@NotNull @Size(min = 1, max = 120) String name, Integer priority) {
		this.name = name;
		this.priority = priority;
	}
	
	public ToDo() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, priority);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		//ToDo other = (ToDo) obj;
		//return id == other.id && Objects.equals(name, other.name) && Objects.equals(priority, other.priority);
		return true;
	}
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", name=" + name + ", priority=" + priority + "]";
	}


}
