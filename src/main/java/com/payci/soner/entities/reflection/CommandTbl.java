package com.payci.soner.entities.reflection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.payci.soner.entities.base.BaseEntity;

@Entity
@Table(name = "CommandTbl")
public class CommandTbl extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CommandTbl() {}
	
	public CommandTbl(String name, String packageName) {
		this.name = name;
		this.packageName = packageName;
	}
	
	public CommandTbl(long id, String name, String packageName) {
		this(name, packageName);
		this.id = id;
	}
	
	private String name;
	
	@Column(name = "package_name")
	private String packageName;

	@OneToMany(mappedBy = "commandTbl", cascade = {CascadeType.ALL})
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private List<MethodTbl> methods = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public List<MethodTbl> getMethods() {
		return Collections.unmodifiableList(methods);
	}

	public void setMethods(List<MethodTbl> methods) {
		this.methods = methods;
	}

	public void addMethod(MethodTbl method) {
		this.methods.add(method);
		method.setCommandTbl(this);
	}
}
