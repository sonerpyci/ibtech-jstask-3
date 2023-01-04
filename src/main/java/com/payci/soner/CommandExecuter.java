package com.payci.soner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.payci.soner.entities.reflection.CommandTbl;
import com.payci.soner.entities.reflection.MethodTbl;
import com.payci.soner.entities.reflection.ParamTbl;
import com.payci.soner.helpers.ReflectionHelper;
import com.payci.soner.helpers.data.carrier.Bag;
import com.payci.soner.hibernate.MethodTblRepository;

public class CommandExecuter {
	
	public Bag PrepareBag(List<ParamTbl> parameters) {
		Bag paramBag = new Bag();
		try (Scanner scanner = new Scanner(System.in)) {
			for(ParamTbl parameter : parameters) {
				System.out.print("Enter value for Parameter " + parameter.getName() + " :");
				String paramVal = scanner.nextLine();
				paramBag.put(parameter.getName(), paramVal);
			}
			return paramBag;
		} catch (Exception e) {
			System.out.println("\nException occured when filling bag params.");
			return paramBag;
		}
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
		
		Bag paramBag = PrepareBag(methodTbl.getParameters());
		
		Object rv = method.invoke(clsObj, paramBag);
		
		System.out.println(rv);
	}
	
	public void Execute(String commandName, Bag paramBag) throws Exception {
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
		
		Object rv = method.invoke(clsObj, paramBag);
		
		System.out.println(rv);
	}
}
