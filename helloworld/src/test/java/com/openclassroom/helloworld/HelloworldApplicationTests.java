package com.openclassroom.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // Lors de l'execution des tests, initialise le contexte Spring (les beans peuvent être utilisées)
class HelloworldApplicationTests {

	@Autowired
	private BusinessService bs;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetHelloWorld() {

		String expected = "Hello World!";

		String result = bs.getHelloWorld().getValue();
		assertEquals(expected, result);
	}

}
