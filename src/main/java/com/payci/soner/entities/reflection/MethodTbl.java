package com.payci.soner.entities.reflection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.payci.soner.entities.base.BaseEntity;
import com.payci.soner.helpers.ReflectionHelper;

@Entity
@Table(name = "MethodTbl")
public class MethodTbl extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MethodTbl() {}
	
	public MethodTbl(String name, String commandName) {
		this.name = name;
		this.setCommandName(commandName);
	}
	
	public MethodTbl(String name, String commandName, ArrayList<ParamTbl> parameters) {
		this.name = name;
		this.setCommandName(commandName);
		this.parameters = parameters;
	}
	
//	public MethodTbl(long id, String name, String commandName, String parameters) {
//		this(name, commandName, ReflectionHelper.DeserializeClassTypes(parameters));
//		this.id = id;
//	}
	
	private String name;
	
	@Column(name = "command_name")
	private String commandName;
	
	
	@OneToMany(mappedBy = "methodTbl", cascade = {CascadeType.ALL})
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private List<ParamTbl> parameters = new ArrayList<>();;
	
	@ManyToOne
    @JoinColumn(name="class_id")
    private CommandTbl commandTbl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String getCommandName() {
		return commandName;
	}

	private void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	
	public List<ParamTbl> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<ParamTbl> parameters) {
		this.parameters = parameters;
		for(ParamTbl param : parameters) {
			param.setMethodTbl(this);
		}
	}
	
	public void addParameter(ParamTbl parameter) {
		this.parameters.add(parameter);
		parameter.setMethodTbl(this);
	}

	public CommandTbl getCommandTbl() {
		return commandTbl;
	}

	public void setCommandTbl(CommandTbl commandTbl) {
		this.commandTbl = commandTbl;
	}
}
