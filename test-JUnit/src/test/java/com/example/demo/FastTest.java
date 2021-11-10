package com.example.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Target(ElementType.METHOD) //메서드에서 사용할 것이라고 명시.
@Retention(RetentionPolicy.RUNTIME) //이 어노테이션을 사용한 코드가 런타임 될 동안까지 실행.
@Test	//메타 어노테이션
@Tag("fast")	//메타 어노테이션
public @interface FastTest { //다른 어노테이션을 조합해서 만든 어노테이션

}
