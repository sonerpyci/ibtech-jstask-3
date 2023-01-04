package com.payci.soner.helpers.data.carrier;

import java.util.HashMap;

public class Bag {
	private final HashMap<String, Object> _hashMap = new HashMap<String, Object>();
	
	public boolean put(String key, Object obj) {
		if(obj == null) return false;
		_hashMap.put(key, obj);
		return true;
	}
	
	public Object get(String key) {
		return _hashMap.get(key);
	}
	
	public Object remove(String key) {
		return _hashMap.remove(key);
	}
	
	public void clear() {
		_hashMap.clear();
	}

	
	// TODO : what if value is another hashMap or similar?
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		_hashMap.forEach((key, value) -> {
			sb.append(key)
			.append(": ")
			.append(value)
			.append('\n');
		});
		
		return sb.toString();
	}
}
