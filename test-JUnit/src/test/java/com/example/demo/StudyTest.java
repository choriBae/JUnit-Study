package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //해당 클래스 전역에 이름에 _를 공백으로 치완한다.
class StudyTest {

	@Test
	@DisplayName("스터디 만들기 ╯°□°）╯") //test이름을 원하는 문자로 변경 할 수 있다. 이게 더 좋아보임
	void test() {
		//fail("Not yet implemented");
		
		assertTimeout(Duration.ofMillis(300), () -> { //300밀리초를 설정하여 테스트를 진행
			new Study(10); //구문중 실행이 300밀리초 내에 끝나야 함
			Thread.sleep(300); //쓰레드에 300밀리초의 딜레이를 줌으로써 예외를 발생시켜본다.
		});
		
		/*
		IllegalArgumentException exception =
				assertThrows(IllegalArgumentException.class, ()-> new Study(-10));
		String message = exception.getMessage();
		assertEquals("limit은 0보다 커야 한다.", exception.getMessage());
		*/
		/*
		Study study = new Study(-10);
		assertAll(
				()-> assertNotNull(study), //객체가 Null인지 아닌지 확인
				()-> assertEquals(StudyStatus.DRAFT , study.getStatus(),
						() -> "대충 메시지를 넣을 수 있다는 내용"), //실제 값이 기대한 값과 같은지 확인, Status가 Null이면 에러가 난다,
				()-> assertTrue(study.getLimit() > 0, "스터디 참가인원은 0보다 커야 한다.")
		);
		*/
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
