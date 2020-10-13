package io.sl.ex.java8.streams;

import static io.sl.ex.java8.streams.GroupingCollectors.*;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import static java.util.stream.Collectors.toList;

import io.sl.ex.java8.streams.Person;

public class GroupingCollectorsTest {
	
	@Test
	public void testManipulate() {
		String prefix = "Master of magic - ";
		Person alice = new Person(1000, "Alice", 18);
		Person bob = new Person(2000, "Bob", 11);
		Person jim = new Person(3000, "Jim", 10);
		Person carl = new Person(3000, "Carl", 11);
		List<Person> persons = ImmutableList.of(alice, bob, jim, carl);    
		
		List<Person> other = manipulate(persons, prefix);
		
		other.stream().forEach(p->{
			assertTrue("Name with prefix is expected", p.getName().contains(prefix));
		});
	}
	
	@Test
	public void testCursor() {
		Stream<Person> cursor = openCursor();
		
		assertTrue("Not empty collection is expected", cursor.collect(toList()).size() > 0);
	}
	
	@Test
	public void testGroupById() {
		Person alice = new Person(1000, "Alice", 18);
		Person bob = new Person(2000, "Bob", 11);
		Person jim = new Person(3000, "Jim", 10);
		Person carl = new Person(3000, "Carl", 11);
		List<Person> persons = ImmutableList.of(alice, bob, jim, carl);    
		
		Map<Long, List<Person>> idToPersons = groupById(persons);
		assertEquals("The same ids are expected after grouping", idToPersons.keySet(), setOf(
				alice.getId(), bob.getId(), jim.getId(), carl.getId())
				);
//		assertEquals(idToPersons.values(), listOf(
//				alice, bob, jim, carl)
//				);        
	}
	
	@Test
	public void testGroupByIdWithEmptyList() {
		Map<Long, List<Person>> idToPersons = groupById(Collections.emptyList());
		
		assertTrue(idToPersons.isEmpty());
	}
	
	@Test
	public void testGroupByNameToIds() {
		Person alice = new Person(1000, "Alice", 18);
		Person bob = new Person(2000, "Bob", 11);
		Person jim = new Person(3000, "Jim", 10);
		Person carl = new Person(4000, "Jim", 11);
		List<Person> persons = ImmutableList.of(alice, bob, jim, carl);    
		
		Map<String, List<Long>> nameToIds = groupByNameToIds(persons);
		
		assertTrue(nameToIds.keySet().stream().count() < persons.size());
		
		assertEquals(nameToIds.keySet(), setOf(
				alice.getName(), bob.getName(), jim.getName(), carl.getName())
				);        
//		assertEquals(nameToIds.values(), listOf(
//		bob.getId(), alice.getId(), jim.getId(), carl.getId())
//		);        
	}

	@Test
	public void testGroupByNameToIdsWithEmptyList() {
		Map<String, List<Long>> nameToIds = groupByNameToIds(Collections.emptyList());
		
		assertTrue(nameToIds.isEmpty());        
	}
	
/*
public class GroupingCollectorsTest {

    
    @Test
    public void testGroupByNameToDistinctAges() {
        Person alice = new Person(1000, "Alice", 18);
        Person bob = new Person(2000, "Bob", 11);
        Person alice2 = new Person(3000, "Alice", 10);
        Person bob2 = new Person(4000, "Bob", 11);
        Person jim = new Person(5000, "Jim", 10);
        List<Person> persons = ImmutableList.of(alice, bob, alice2, bob2, jim);    
        
        Map<String, Set<Integer>> nameToAges = groupByNameToDistinctAges(persons);
        
        assertThat(nameToAges).containsOnly(entry(alice.getName(), setOf(alice.getAge(), alice2.getAge())), entry(bob.getName(), setOf(bob.getAge())), entry(jim.getName(), setOf(jim.getAge())));
    }
    
    @Test
    public void testGroupByNameToDistinctAgesWithEmptyList() {
        Map<String, Set<Integer>> nameToAges = groupByNameToDistinctAges(Collections.emptyList());
        
        assertThat(nameToAges).isEmpty();
    }
    
    @Test
    public void testGroupByNameToSumOfAges() {
        Person alice = new Person(1000, "Alice", 18);
        Person bob = new Person(2000, "Bob", 11);
        Person alice2 = new Person(3000, "Alice", 10);
        List<Person> persons = ImmutableList.of(alice, bob, alice2);      
        
        Map<String, Integer> nameToSum = groupByNameToSumOfAges(persons);
        
        assertThat(nameToSum).containsOnly(entry(alice.getName(), alice.getAge() + alice2.getAge()), entry(bob.getName(), bob.getAge()));
    }
    
    @Test
    public void testGroupByNameToSumOfAgesWithEmptyList() {
        Map<String, Integer> nameToSum = groupByNameToSumOfAges(Collections.emptyList());
        
        assertThat(nameToSum).isEmpty();
    }

    @Test
    public void testGroupByAgeToNameToTotal() {
        Person alice = new Person(1000, "Alice", 18);
        Person bob = new Person(2000, "Bob", 11);
        Person carl = new Person(3000, "Carl", 11);
        Person bob2 = new Person(4000, "Bob", 11);
        List<Person> persons = ImmutableList.of(alice, bob, carl, bob2);  
        
        Map<Integer, Map<String, Long>> ageToNameToTotal = groupByAgeToNameToCount(persons);
        
        assertThat(ageToNameToTotal).containsOnlyKeys(alice.getAge(), bob.getAge());
        assertThat(ageToNameToTotal.get(alice.getAge())).containsOnly(entry(alice.getName(), 1L));
        assertThat(ageToNameToTotal.get(bob.getAge())).containsOnly(entry(bob.getName(), 2L), entry(carl.getName(), 1L));
    }
    
    @Test
    public void testGroupByAgeToNameToTotalWithEmptyList() {
        Map<Integer, Map<String, Long>> ageToNameToTotal = groupByAgeToNameToCount(Collections.emptyList());
        
        assertThat(ageToNameToTotal).isEmpty();
    }
    
    @Test
    public void testPartitionByIsAllowedToVote() {
        Person alice = new Person(1000, "Alice", 18);
        Person bob = new Person(2000, "Bob", 21);
        Person jim = new Person(3000, "Jim", 10);
        Person carl = new Person(4000, "Carl", 11);
        List<Person> persons = ImmutableList.of(alice, bob, jim, carl);       
        
        Map<Boolean, List<Person>> voters = partitionByIsAllowedToVote(persons);
        
        assertThat(voters).containsOnly(entry(true, listOf(alice, bob)), entry(false, listOf(jim, carl)));
    }    
    
    @Test
    public void testPartitionByIsAllowedToVoteWithEmptyList() {
        Map<Boolean, List<Person>> voters = partitionByIsAllowedToVote(Collections.emptyList());
        
        assertThat(voters).containsOnly(entry(true, Collections.emptyList()), entry(false, Collections.emptyList()));
    }    
*/   
    @SafeVarargs
    private static <T> List<T> listOf(T... items) {
        return ImmutableList.copyOf(items);
    }
    
    @SafeVarargs
    private static <T> Set<T> setOf(T... items) {
        return ImmutableSet.copyOf(items);
    }
}
