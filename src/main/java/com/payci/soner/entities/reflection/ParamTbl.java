package com.payci.soner.entities.reflection;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.payci.soner.entities.base.BaseEntity;

@Entity
@Table(name = "ParamTbl")
public class ParamTbl extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name="method_id")
    private MethodTbl methodTbl;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MethodTbl getMethodTbl() {
		return methodTbl;
	}

	public void setMethodTbl(MethodTbl methodTbl) {
		this.methodTbl = methodTbl;
	}
}
