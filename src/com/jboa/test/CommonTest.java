package com.jboa.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CommonTest {

	@Test
	public void testMap (){
		Map<String, Integer> map = new HashMap<>();
		map.put("1", null);
		map.put("2", null);
		System.out.println(map);
	}
	@Test
	public void testInteger (){
		Integer integer= new Integer(3);
		Integer integer2 = new Integer(3);
		System.out.println(integer.equals(integer2));
	}
	@Test
	public void testList (){
		List<Integer> iList = new ArrayList<>();
		Integer integer = new Integer(3);
		iList.add(integer);
		integer = 4;
		iList.add(integer);
		System.out.println(iList);
	}
	@Test
	public void testString (){
		String string = "abcd";
		System.out.println(string.length());
	}
}
