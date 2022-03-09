package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datastruct.*;

class MyUnsortedListTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_isEmpty() {
		UnsortedList<Integer> ul;

		ul = MyUnsortedList.of(0,1,2,3);
		assertFalse(ul.isEmpty());

		ul = MyUnsortedList.of();
		assertTrue(ul.isEmpty());

	}

	@Test
	void test_size() {
		UnsortedList<Integer> ul;
		int s;

		ul = MyUnsortedList.of(0,1,2,3);
		s = ul.size();
		assertEquals(s, 4);

		ul = MyUnsortedList.of();
		s = ul.size();
		assertEquals(s, 0);
	}

	@Test
	void test_equals() {
		UnsortedList<Integer> ul1, ul2;
		int e;

		ul1 = MyUnsortedList.of();
		assertFalse(ul1.equals(new Integer(5)));

		ul1 = MyUnsortedList.of();
		ul2 = MyUnsortedList.of();
		assertTrue(ul1.equals(ul2));

		ul1 = MyUnsortedList.of(1,8,5);
		ul2 = MyUnsortedList.of();
		assertFalse(ul1.equals(ul2));

		ul1 = MyUnsortedList.of();
		ul2 = MyUnsortedList.of(5,9,7,3);
		assertFalse(ul1.equals(ul2));

		ul1 = MyUnsortedList.of(8,6,4);
		ul2 = MyUnsortedList.of(8,6,4);
		assertTrue(ul1.equals(ul2));


		ul1 = MyUnsortedList.of(1,2,3);
		ul2 = MyUnsortedList.of(3,2,1);
		assertFalse(ul1.equals(ul2));

		ul1 = MyUnsortedList.of(1,2);
		ul2 = MyUnsortedList.of(3,2,1);
		assertFalse(ul1.equals(ul2));

		ul1 = MyUnsortedList.of(1,2,3);
		ul2 = MyUnsortedList.of(2,1);
		assertFalse(ul1.equals(ul2));

		ul1 = MyUnsortedList.of(1,2);
		ul2 = MyUnsortedList.of(1,2,3);
		assertFalse(ul1.equals(ul2));

		ul1 = MyUnsortedList.of(1,2,3);
		ul2 = MyUnsortedList.of(1,2);
		assertFalse(ul1.equals(ul2));
	}

	@Test
	void test_prepend() {
		UnsortedList<Integer> ul1, ul2;
		int e;

		// equals
		ul1 = MyUnsortedList.of(0,1,2,3);
		ul1.prepend(2);
		ul2 = MyUnsortedList.of(2,0,1,2,3);
		assertTrue(ul1.equals(ul2));

		ul1 = MyUnsortedList.of();
		ul1.prepend(3);
		ul2 = MyUnsortedList.of(3);
		assertTrue(ul1.equals(ul2));

		// isEmpty
		ul1 = MyUnsortedList.of();
		ul1.prepend(0);
		assertFalse(ul1.isEmpty());

		// size
		ul1 = MyUnsortedList.of();
		ul1.prepend(0);
		assertEquals(ul1.size(), 1);

		ul1 = MyUnsortedList.of(0,1,2,3);
		ul1.prepend(4);
		assertEquals(ul1.size(), 5);
	}

	@Test
	void test_append() {
		UnsortedList<Integer> ul1, ul2;
		int e;

		// equals
		ul1 = MyUnsortedList.of(0,1,2,3);
		ul1.append(2);
		ul2 = MyUnsortedList.of(0,1,2,3,2);
		assertTrue(ul1.equals(ul2));

		ul1 = MyUnsortedList.of();
		ul1.append(3);
		ul2 = MyUnsortedList.of(3);
		assertTrue(ul1.equals(ul2));

		// isEmpty
		ul1 = MyUnsortedList.of();
		ul1.append(0);
		assertFalse(ul1.isEmpty());

		// size
		ul1 = MyUnsortedList.of();
		ul1.append(0);
		assertEquals(ul1.size(), 1);

		ul1 = MyUnsortedList.of(0,1,2,3);
		ul1.append(4);
		assertEquals(ul1.size(), 5);

	}

	@Test
	void test_insert() {
		IndexOutOfBoundsException thrown;
		UnsortedList<Integer> ul1;
		UnsortedList<Integer> ul2;

		// avant
		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of();
			ul.insert(1, -20);
		});

		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of(1,2,3);
			ul.insert(1, -20);
		});

		// ****** dedans

		// liste vide 
		ul1 = MyUnsortedList.of();
		ul1.insert(1, 0);
		// size
		assertEquals(ul1.size(), 1);
		// equals
		ul2 = MyUnsortedList.of(1);
		assertTrue(ul1.equals(ul2));
		// isEmpty
		assertFalse(ul1.isEmpty());

		// liste non vide 

		// debut
		ul1 = MyUnsortedList.of(1,2,3);
		ul1.insert(4, 0);
		// size
		assertEquals(ul1.size(), 4);
		// equals
		ul2 = MyUnsortedList.of(4,1,2,3);
		assertTrue(ul1.equals(ul2));
		// isEmpty
		assertFalse(ul1.isEmpty());

		// milieu
		ul1 = MyUnsortedList.of(1,2,3);
		ul1.insert(4, 2);
		// size
		assertEquals(ul1.size(), 4);
		// equals
		ul2 = MyUnsortedList.of(1,2,4,3);
		assertTrue(ul1.equals(ul2));
		// isEmpty
		assertFalse(ul1.isEmpty());

		// fin
		ul1 = MyUnsortedList.of(1,2,3);
		ul1.insert(4, 3);
		// size
		assertEquals(ul1.size(), 4);
		// equals
		ul2 = MyUnsortedList.of(1,2,3,4);
		assertTrue(ul1.equals(ul2));
		// isEmpty
		assertFalse(ul1.isEmpty());

		// après
		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of();
			ul.insert(1, 62);
		});
		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of(1,2,3);
			ul.insert(1, 62);
		});
	}


	@Test
	void test_pop() {
		EmptyListException thrown;
		UnsortedList<Integer> ul1, ul2;
		int i;

		//sur liste vide

		thrown = assertThrows(EmptyListException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of();
			ul.pop();
		});

		//sur liste a un seul element
		ul1 = MyUnsortedList.of(30);
		i = ul1.pop();
		ul2 = MyUnsortedList.of();
		assertTrue(ul1.equals(ul2));
		assertEquals(ul1.size(),0);
		assertTrue(ul1.isEmpty());
		assertEquals(i, 30);


		//sur liste a plusieurs elements
		ul1 = MyUnsortedList.of(20,1,2,3,4,5,6,7,8,9,10);
		i = ul1.pop();
		ul2 = MyUnsortedList.of(1,2,3,4,5,6,7,8,9,10);
		assertTrue(ul1.equals(ul2));
		assertEquals(i,20);
		assertEquals(ul1.size(),10);
		assertFalse(ul1.isEmpty());

	}

	@Test
	void test_remove() {
		IndexOutOfBoundsException thrown;
		UnsortedList<Integer> ul1;
		UnsortedList<Integer> ul2;
		int i;

		// avant
		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of();
			ul.remove(-20);
		});

		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of(1,2,3);
			ul.remove(-20);
		});


		// ****** dedans

		// liste vide 
		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of();
			ul.remove(0);
		});

		// liste non vide 

		// premier element
		ul1 = MyUnsortedList.of(1,2,3,4,5);
		i = ul1.remove(0);
		// size
		assertEquals(ul1.size(), 4);
		// equals
		ul2 = MyUnsortedList.of(2,3,4,5);
		assertTrue(ul1.equals(ul2));
		assertEquals(1, i);
		// isEmpty
		assertFalse(ul1.isEmpty());

		// element au milieu
		ul1 = MyUnsortedList.of(1,2,3,4,5);
		i = ul1.remove(2);
		// size
		assertEquals(4, ul1.size()); // 2ème bug
		// equals
		ul2 = MyUnsortedList.of(1,2,4,5);
		assertTrue(ul1.equals(ul2));
		assertEquals(3, i);
		// isEmpty
		assertFalse(ul1.isEmpty());	

		// dernier element
		ul1 = MyUnsortedList.of(1,2,3,4,5);
		i = ul1.remove(4);
		// size
		assertEquals(ul1.size(), 4);
		// equals
		ul2 = MyUnsortedList.of(1,2,3,4);
		assertTrue(ul1.equals(ul2));
		assertEquals(5, i);
		// isEmpty
		assertFalse(ul1.isEmpty());

		// après
		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of();
			ul.remove(62);
		});
		thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of(1,2,3);
			ul.remove(62);
		});
	}

	@Test
	void test_popLast() {
		EmptyListException thrown;
		UnsortedList<Integer> ul1, ul2;
		int i;

		//sur liste vide
		thrown = assertThrows(EmptyListException.class, () -> {
			UnsortedList<Integer> ul;
			ul = MyUnsortedList.of();
			ul.popLast();
		});
		//1er bug :)

		//sur liste a un seul element
		ul1 = MyUnsortedList.of(30);
		i = ul1.popLast();
		ul2 = MyUnsortedList.of();
		assertTrue(ul1.equals(ul2));
		assertEquals(ul1.size(),0);
		assertTrue(ul1.isEmpty());
		assertEquals(i, 30);


		//sur liste a plusieurs elements
		ul1 = MyUnsortedList.of(20,1,2,3,4,5,6,7,8,9,10);
		i = ul1.popLast();
		assertEquals(i,10);
		ul2 = MyUnsortedList.of(20,1,2,3,4,5,6,7,8,9);
		assertTrue(ul1.equals(ul2));
		assertEquals(ul1.size(),10);
		assertFalse(ul1.isEmpty());

	}



}
