import org.junit.jupiter.api.*;

public class LifeCycleTest {

	@BeforeAll
	static void runOnceBeforeAll(){
		System.out.println("runOnceBeforeAll");
	}
	
	@AfterAll
	static void runOnceAfterAll(){
		System.out.println("runOnceAfterAll");
	}
	
	@BeforeEach
	void runBeforeEachTest(){
		System.out.println("runBeforeEachTest");
	}
	
	@AfterEach
	void runAfterEachTest(){
		System.out.println("runAfterEachTest");
	}
	
	public LifeCycleTest() {
		System.out.println("LifeCycleTest");
	}
	
	@Test
	void testOne(){
		System.out.println("testOne");
	}
	
	@Test
	void testTwo(){
		System.out.println("testTwo");
	}
	
	@Test
	void testThree(){
		System.out.println("testThree");
	}
	
	
}
