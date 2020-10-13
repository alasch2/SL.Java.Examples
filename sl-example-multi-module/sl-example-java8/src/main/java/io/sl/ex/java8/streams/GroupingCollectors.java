package io.sl.ex.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


public class GroupingCollectors {
	
	private final static long initialId = 1000;
	private static List<Person> internalRepository;
	
	static {
		long ctr = initialId;
		internalRepository = Arrays.asList(
		new Person(ctr, "Alice", 18),
		new Person(++ctr, "Bob", 11),
		new Person(++ctr, "Jim", 10),
		new Person(++ctr, "Carl", 11)
		);
	}
	
	public static List<Person> manipulate(List<Person> persons, String namePrefix) {
		return persons.stream()
				.map(p-> {
					p.setName(namePrefix + p.getName());
					return p;
				})
				.collect(toList())
				.stream()
				.map(p-> {
					p.makeOlder(c-> c.getAge() + 1);
					return p;
				})
//				.map(p-> {
//					p.setAge(p.getAge() +1);
//					return p;
//				})
				.collect(toList());
	}
	
	public static Stream<Person> openCursor() {
		return internalRepository.stream();
	}

    /**
     * Example:
     * p1 { name: "Alice", id: 3300 }, p2 { name: "Dan", id: 2400 }, p3 { name: "Bob", id: 1500 } =>
     * HashMap { 3300 => p1, 2400 => p2, 1500 => p3 }
     */    
    public static Map<Long, List<Person>> groupById(List<Person> persons) {
        return persons.stream()
        		.collect(groupingBy(Person::getId));
    }

    /**
     * Example:
     * p1 { name: "Alice", id: 3300 }, p2 { name: "Dan", id: 2400 }, p3 { name: "Dan", id: 1500 } =>
     * HashMap { "Alice" => { 3300 }, "Dan" => { 2400, 1500 } }
     */     
    public static Map<String, List<Long>> groupByNameToIds(List<Person> persons) {
        return persons.stream()
        		.collect(groupingBy(Person::getName, mapping(Person::getId, toList())));
    }

    /**
     * Example:
     * p1 { id: 1001, name: "Alice", age: 33 }, p2 { id: 1002, name: "Alice", age: 33 },
(     * p3 { id: 1003, name: "Bob", age: 33 }, p4 { id: 1004, name: "Bob", age: 37 } =>
     * HashMap { "Alice" => { 33 }, "Bob" => { 33, 37 } }
     */    
    public static Map<String, Set<Integer>> groupByNameToDistinctAges(List<Person> persons) {
        return persons.stream()
        		.collect(groupingBy(Person::getName, mapping(Person::getAge, toSet())));
    }
    
    
    /**
     * Example:
     * p1 { name: "Alice", age: 33 }, p2 { name: "Dan", age: 24 }, p3 { name: "Alice", age: 10 } =>
     * HashMap { "Alice" => 43, "Dan" => 24 }
     */     
    public static Map<String, Integer> groupByNameToSumOfAges(List<Person> persons) {
        return persons.stream()
        		.collect(groupingBy(Person::getName, summingInt(Person::getAge)));
    }

    /**
     * Example:
     * p1 { id: 1001, name: "Alice", age: 33 }, p2 { id: 1002, name: "Dan", age: 25 },
     * p3 { id: 1003, name: "Bob", age: 33 }, p4 { id: 1004, name: "Bob", age: 33 } =>
     * HashMap { 25 => { "Dan" => 1 }, 33 => { "Alice" => 1, "Bob" => 2 } }
     */
    public static Map<Integer, Map<String, Long>> groupByAgeToNameToCount(List<Person> persons) {
        return persons.stream()
        		.collect(groupingBy(Person::getAge, groupingBy(Person::getName, counting())));
    }
    
    /**
     * Example:
     * p1 { name: "Alice", age: 17 }, p2 { name: "Dan", age: 18 }, p3 { name: "Jim", age: 54 } =>
     * HashMap { true => { "Dan", "Jim" }, false => { "Alice" }}
     */     
    public static Map<Boolean, List<Person>> partitionByIsAllowedToVote(List<Person> persons) {
        return persons.stream()
        		.collect(partitioningBy(p->p.getAge() >= 18));
    }    
}

