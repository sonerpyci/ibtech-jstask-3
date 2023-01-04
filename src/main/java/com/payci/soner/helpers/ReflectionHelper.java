package com.payci.soner.helpers;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReflectionHelper {

	public static Class<?> ExtractClassType(String className) {
		try {
			Class<?> cls = Class.forName(className);
			return cls;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return String.class;
		}
	}
	
	public static String SerializeTypes(ArrayList<Class<?>> classTypes) {
		StringBuilder sb = new StringBuilder();
		Iterator<Class<?>> iterator = classTypes.iterator();
		
		while (iterator.hasNext()) {
			sb.append(iterator.next().getSimpleName());
			sb.append(",");
		}
		return sb.toString();
	}
	
	public static ArrayList<Class<?>> DeserializeClassTypes(String classTypesStr) {
		String[] classTypesArr = classTypesStr.split(",");
		ArrayList<Class<?>> classTypes = new ArrayList<Class<?>>();
		
		for(String classTypeStr : classTypesArr) {
			Class<?> classType;
			try {
				classType = Class.forName(classTypeStr);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				classType = String.class;
			}
			classTypes.add(classType);
		}
		return classTypes;
	}
	
	public static List<Method> getAllMethods(Class<?> cls) {
		List<Method> suitableMethods = new ArrayList<Method>();
		do {
			System.out.println(cls.getSimpleName());
			suitableMethods.addAll(Arrays.asList(cls.getDeclaredMethods()));
			cls = cls.getSuperclass();
		} while (cls.getSuperclass() != null && cls.getSuperclass().getSimpleName() != "Object");
		
		return suitableMethods;
	}
	
	public static Method getMethod(Class<?> cls, String methodName) {
		Method suitableMethod = null;
		do {
			System.out.println(cls.getSimpleName());
			// check if list contains desired method.
			// if method not exists in existing method list, look for super classes until
			// there is no super class or method found.
			for(Method method : cls.getDeclaredMethods()) {
				if (method.getName().equals(methodName)) {
					suitableMethod = method;
					return suitableMethod;
				}
			}
			
			cls = cls.getSuperclass();
		} while (suitableMethod == null || cls.getSuperclass().getSimpleName() != "Object");
		
		return suitableMethod;
	}
	
	public static List<String> getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        List<String> parameterNames = new ArrayList<>();

        for (Parameter parameter : parameters) {
            if(parameter.isNamePresent()) {
            	String parameterName = parameter.getName();
                parameterNames.add(parameterName);
            }
        }
        return parameterNames;
    }
	
	public static ArrayList<Class<?>> getParameterTypes(Method method) {
		Class<?>[] parameters = method.getParameterTypes();
        ArrayList<Class<?>> parameterTypes = new ArrayList<>();

        for (Class<?> parameter : parameters) {
        	System.out.println(parameter.getName());
        	parameterTypes.add(parameter);
        	
//        	if(parameter.isNamePresent()) {
//            	 Class<?> parameterType = parameter.getType();
//                 parameterTypes.add(parameterType);
//            } else if (parameter.getDeclaringExecutable().getDeclaringClass().getSimpleName().equals("BaseRepository")) {
//            	Class<?> parameterType = BaseEntity.class;
//            	
//            }
        }
        return parameterTypes;
    }
}
