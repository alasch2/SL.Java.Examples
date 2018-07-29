package io.sl.ex.java8.streams;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lombok.Data;

@Data
public class Person {

	private long id;
	private String name;
	private int age;
	private List<Person> children;

	public Person(long id, String name, int age, Person... children) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.children = ImmutableList.copyOf(children);
	}
	
	public void makeOlder(MakeOlder ager) {
		age = ager.run(this);
	}

	@Override
	public String toString() {
		return String.format("Person (id=%s, name=%s, age=%s, children=%s)", 
				id, name, age, children);
	}
	
	@FunctionalInterface
	static interface MakeOlder {
		int run(Person p);
	}
	
}
