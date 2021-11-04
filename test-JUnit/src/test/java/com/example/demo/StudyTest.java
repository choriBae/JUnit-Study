package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //해당 클래스 전역에 이름에 _를 공백으로 치완한다.
class StudyTest {

	@Test
	@DisplayName("╯°□°）╯") //test이름을 원하는 문자로 변경 할 수 있다. 이게 더 좋아보임
	void test() {
		//fail("Not yet implemented");
		
		Study study = new Study();
		assertNotNull(study); //객체가 Null이 아닐경우 true
		System.out.println("test1");
	}
	
	@Test
	//@Disabled //사용하지 않는 메서드일 경우에 disabled한다.
	void test2_tt_pp() {
		//fail("Not yet implemented");
		System.out.println("test2");
	}
	
	@BeforeAll	//모든 test코드가 실행되기 전에 딱 한번 호출된다.
	static void beforeAll() { //BeforeAll을 구현할 때는 static을 사용해야 한다, 리턴 타입이 존재할 수 없고 void만 가능하다.
		System.out.println("before all");
	}
	
	@AfterAll	//모든 test코드가 실행된 후 딱 한번 호출된다.
	static void afterAll() { 
		System.out.println("after all");
	}
	
	@BeforeEach //각각의 test를  실행하기 이전에 호출된다.
	void beforeEach() { //All과 달리 static일 필요는 없다. 
		System.out.println("before Each");
	}
	
	@AfterEach //각각의 test를  실행한 이후에 호출된다.
	void afterEach() { 
		System.out.println("after Each");
	}
}
