package com.payci.soner;

import java.lang.reflect.Method;
import java.util.Scanner;

import com.payci.soner.entities.reflection.CommandTbl;
import com.payci.soner.entities.reflection.MethodTbl;
import com.payci.soner.helpers.ReflectionHelper;
import com.payci.soner.hibernate.MethodTblRepository;

public class CommandExecuter {
	
	public void Prepare() {
		
	}
	
	public void Execute(String commandName) throws Exception {
		StringBuilder sb = new StringBuilder();
		MethodTblRepository methodTblRepository = new MethodTblRepository();
		MethodTbl methodTbl = methodTblRepository.getByCommandName(commandName);
		CommandTbl commandTbl = methodTbl.getCommandTbl();
		String fullClassPath = sb.append(commandTbl.getPackageName())
				.append('.')
				.append(commandTbl.getName())
				.toString();
		
		Class<?> cls = ReflectionHelper.ExtractClassType(fullClassPath);
		Object clsObj = cls.getDeclaredConstructor().newInstance();
		
		Method method = ReflectionHelper.getMethod(cls, methodTbl.getName());
		
		Object rv = method.invoke(clsObj);
	}
}
