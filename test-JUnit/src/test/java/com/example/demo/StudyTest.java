package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //해당 클래스 전역에 이름에 _를 공백으로 치완한다.
@TestInstance(Lifecycle.PER_CLASS) //클래스 마다 인스턴스를 생성하기 때문에, 이 클래스에 있는 모든 메서드가 하나의 인스턴스를 공유하게 된다.
@TestMethodOrder(OrderAnnotation.class)
class StudyTest {

	int value = 1;
	
	@Test
	@DisplayName("스터디 만들기 ╯°□°）╯") //test이름을 원하는 문자로 변경 할 수 있다. 이게 더 좋아보임
	@EnabledOnOs(OS.WINDOWS)
	@EnabledOnJre(value = JRE.JAVA_8)
	//@EnabledIfSystemProperty(named = "java.version", matches = "1.8.0_202")
	//@EnabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation")
	//@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
	//@Tag("fast")
	@FastTest
	void test() {
		//fail("Not yet implemented");
		//Study actual = new Study(10);	//생성자 만들기

		//System.out.println(System.getProperty("java.version"));
		//System.out.println(System.getProperty("java.vendor"));
		//System.out.println(System.getProperty("java.home"));
		
		//String test_env = System.getenv("TEST_ENV");
		//System.out.println(test_env);
		//assumeTrue("LOCAL".equalsIgnoreCase(test_env));	//로컬인 경우에만 테스트를 실행한다.(조건)
		 
		//assumingThat("LOCAL".equalsIgnoreCase(test_env), ()-> assertThat(actual.getLimit()).isGreaterThan(0));
		
		/*
		assertTimeout(Duration.ofMillis(300), () -> { //300밀리초를 설정하여 테스트를 진행
			new Study(10); //구문중 실행이 300밀리초 내에 끝나야 함
			Thread.sleep(300); //쓰레드에 300밀리초의 딜레이를 줌으로써 예외를 발생시켜본다.
		});
		*/
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
	
	@Order(2)
	//테스트 간 의존성을 없게 만들어야 한다.
	//의존성이 있을 경우 실행순서에 문제가 생긴다.
	@Test
	@FastTest
	void test1_tt_pp() {
		System.out.println(this);
		System.out.println(value++);
		Study actual = new Study(value++);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Order(1)
	@Test
	//@Tag("slow")
	@SlowTest
	//@Disabled //사용하지 않는 메서드일 경우에 disabled한다.
	void test2_tt_pp() {
		System.out.println(this);
		Study actual = new Study(value++);
		System.out.println(value++);
	}
	
	@Order(5)
	@DisplayName("반목문 테스트1")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")	//value = 반복횟수, name = 테스트명
	@EmptySource //비어있는 문자열의 인자를 하나 더 추가한다.
	@NullSource //Null의 인자를 하나 더 추가해준다.
	void repeatedTest(RepetitionInfo repetitionInfo) {	//RepetitionInfo 인자를 통해서 몇번째 반복되고 있는지 확인 할 수 있다.
		System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" 
							+ repetitionInfo.getTotalRepetitions());
	}
	
	@Order(4)
	@DisplayName("반목문 테스트2")
	@ParameterizedTest(name = "{index} {displayName} message={0}") //매개변수(파라미터)를 index(0,1,2)로 참조할 수 있다.
	@ValueSource(strings = {"반복문","테스트"})
	//@ValueSource(ints = {10, 20, 40})
	//@CsvSource({"java, 10,", ""}) //여러 인자를 콤마로 구분해서 파라미터로 넘겨줄 수 있다.
	//@EmptySource //비어있는 문자열의 인자를 하나 더 추가한다.
	//@NullSource //Null의 인자를 하나 더 추가해준다.
	//@NullAndEmptySource //Null과 비어있는 문자열을 하나 씩 추가한다.
	//void parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {	//여러 파라미터들을 반복 테스트할 수 있는 방법  / 내가 만든 타입으로 매개변수를 만들어 인자를 받을 수 있다.
	void parameterizedTest1(String massage) {	//여러 파라미터들을 반복 테스트할 수 있는 방법  / 내가 만든 타입으로 매개변수를 만들어 인자를 받을 수 있다.
		//Study study = new Study(limit, name)
		//System.out.println(study.getLimit());
		System.out.println(massage);
	}
	
	@DisplayName("반목문 테스트3")
	@ParameterizedTest(name = "{index} {displayName} message={0}") //매개변수(파라미터)를 index(0,1,2)로 참조할 수 있다.
	@ValueSource(ints = {10, 20, 40}) //여러 인자를 콤마로 구분해서 파라미터로 넘겨줄 수 있다.
	void parameterizedTest2(Integer limit) {
		System.out.println(limit);
	}
	
	@DisplayName("반목문 테스트4")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, '스프링'"}) //여러 인자를 콤마로 구분해서 파라미터로 넘겨줄 수 있다.
	void parameterizedTest3(@ConvertWith(StudyConverter.class) Study study) {	//여러 파라미터들을 반복 테스트할 수 있는 방법  / 내가 만든 타입으로 매개변수를 만들어 인자를 받을 수 있다.
		System.out.println(study.getLimit());
	}
	
	@DisplayName("반목문 테스트5-1")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, '스프링'"})
	void parameterizedTest4(ArgumentsAccessor argumentsAccessor) { //여러개의 인자값들을 받아 조합해서 만든 인스턴스를 테스트 할 수 있다.
		Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		System.out.println(study);
	}
	
	@DisplayName("반목문 테스트5-2")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, '스프링'"})
	void parameterizedTest5(@AggregateWith(StudyAggregator.class) Study study) { //StudyAggregator메서드를 사용하려면 @AggregateWith 어노테이션을 사용한다.
		System.out.println(study);
	}
	
	//인스턴스를 만들지 않고 커스텀 메서드를 만들어 사용하려면.
	static class StudyAggregator implements ArgumentsAggregator { //제약조건 : 반드시 static inner class거나 Public class 여야 한다.
		@Override
		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) {
			return new Study(accessor.getInteger(0), accessor.getString(1)); //0번째 값의 int형 값과, 1번째 값의 String형의 값을 조합하여 리턴한다.
		} 
	}
	
	static class StudyConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study.class, targetType, "Can only convert to Study");
			return new Study(Integer.parseInt(source.toString()));
		}
		
	}

	//@TestInstance를 클래스당 만들면 static일 필요 없다.
	//현재 클래스에서 인스턴스를 만들기 때문에, 메서드들이 static일 필요 없다.
	@BeforeAll	//모든 test코드가 실행되기 전에 딱 한번 호출된다.
	//static void beforeAll() { //BeforeAll을 구현할 때는 static을 사용해야 한다, 리턴 타입이 존재할 수 없고 void만 가능하다.
	void beforeAll() { 
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
