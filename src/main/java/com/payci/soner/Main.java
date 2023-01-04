package com.payci.soner;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

import com.payci.soner.entities.Account;
import com.payci.soner.entities.Address;
import com.payci.soner.entities.Customer;
import com.payci.soner.entities.Phone;
import com.payci.soner.entities.reflection.CommandTbl;
import com.payci.soner.entities.reflection.MethodTbl;
import com.payci.soner.helpers.ReflectionHelper;
import com.payci.soner.hibernate.ClassTblRepository;
import com.payci.soner.hibernate.CustomerRepository;

public class Main {
	static boolean run = true;
	static CommandExecuter commandExecuter = new CommandExecuter();
	
	public static void main(String[] args) {
		//initializeEntities();
		initializeCommands("com.payci.soner.operations", "CustomerOperations");
		initializeCommands("com.payci.soner.operations", "AddressOperations");
		initializeCommands("com.payci.soner.operations", "AccountOperations");
		initializeCommands("com.payci.soner.operations", "PhoneOperations");
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter CommandName: ");  
			String commandName = scanner.nextLine();
			commandExecuter.Execute(commandName);
		} catch (Exception e) {
			System.out.println("\nException occured. Try Again."); 
		}
	}
	
	private static void initializeCommands(String packagePath, String className) {
		StringBuilder sb = new StringBuilder();
		String fullClassPath = sb.append(packagePath)
				.append('.')
				.append(className)
				.toString();
		sb.setLength(0);
		
		ClassTblRepository classTblRepository = new ClassTblRepository();
		
		if (classTblRepository.getByName(className) != null)
			return;
		
		CommandTbl repositoryTbl = new CommandTbl(className, packagePath);
		Class<?> repositoryClass = ReflectionHelper.ExtractClassType(fullClassPath);
		
		List<Method> methods = ReflectionHelper.getAllMethods(repositoryClass);
		
		for(Method method : methods) {
//			ArrayList<Class<?>> parameterTypesOfMethod = ReflectionHelper.getParameterTypes(method);
			String commandName = sb.append(className)
					.append('_')
					.append(method.getName())
					.toString();
			sb.setLength(0);
//			MethodTbl methodTbl = new MethodTbl(method.getName(), commandName, parameterTypesOfMethod);
			MethodTbl methodTbl = new MethodTbl(method.getName(), commandName);
			repositoryTbl.addMethod(methodTbl);
		}
		classTblRepository.save(repositoryTbl);
	}
	
	
	
	private static void testReflection() {
		for(Method declaredMethod : ReflectionHelper.getAllMethods(CustomerRepository.class)) {
			System.out.println(declaredMethod);
		}

		Method singleMethod = ReflectionHelper.getMethod(CustomerRepository.class, "getAll");
		
		System.out.println("single method : " + singleMethod);
	}
	
	private static void initializeEntities() {
		CustomerRepository customerRepository = new CustomerRepository();

		Customer customer = new Customer("Soner", "Paycı");
		customerRepository.saveOrUpdate(customer);

		Address address1 = new Address("Manisa", "Salihli", "45300", "bulunamayan adres.");
		Address address2 = new Address("Gebze", "Merkez", "????", "ibtech adres.");
		customer.addAddres(address1);
		customer.addAddres(address2);
		
		Account account = new Account(0.0);
		customer.addAccount(account);
		
		Phone phone = new Phone("+90", "555 222 11 00");
		customer.addPhone(phone);

		customerRepository.saveOrUpdate(customer);
		
		Customer persistentCustomer = customerRepository.get(customer.getId());
		
		System.out.println(persistentCustomer.toString());
		

		List<Address> persistentAddresses = persistentCustomer.getAddresses();
		for (Address addrs : persistentAddresses) {
			System.out.println(addrs.toString());
		}
	}
}
