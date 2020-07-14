package com.master.text.master.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port+"/ClientApp";
	}
	

	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void testGetConnectedPhiladelphiaToAlbany() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=Philadelphia&destination=Albany", String.class);
		System.out.println("testGetConnectedPhiladelphiaToAlbany  -> "+connected);
		assertNotNull(connected);
		assertEquals("no", connected);
	}
	
	@Test
	public void testGetConnectedBostonToPhiladelphia() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=Boston&destination=Philadelphia", String.class);
		System.out.println("testGetConnectedBostonToPhiladelphia  -> "+connected);		
		assertNotNull(connected);
		assertEquals("yes", connected);
	}
	
	@Test
	public void testGetConnectedBostonToNewark() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=Boston&destination=Newark", String.class);
		System.out.println("testGetConnectedBostonToNewark  -> "+connected);		
		assertNotNull(connected);
		assertEquals("yes", connected);
	}
	
	@Test
	public void testGetConnectedPhiladelphiaToNewark() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=Philadelphia&destination=Newark", String.class);
		System.out.println("testGetConnectedBostonToNewark  -> "+connected);		
		assertNotNull(connected);
		assertEquals("yes", connected);
	}

	
	@Test
	public void testGetConnectedBostonToEmpty() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=Boston&destination=", String.class);
		System.out.println("testGetConnectedBostonToEmpty  -> "+connected);		
		assertNotNull(connected);
		assertEquals("no", connected);
	}
	
	@Test
	public void testGetConnectedEmptyToPhiladelphia() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=&destination=Philadelphia", String.class);
		System.out.println("testGetConnectedEmptyToPhiladelphia  -> "+connected);		
		assertNotNull(connected);
		assertEquals("no", connected);
	}
	
	@Test
	public void testGetConnectedEmptyToEmpty() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=&destination=", String.class);
		System.out.println("testGetConnectedEmptyToEmpty  -> "+connected);
		assertNotNull(connected);
		assertEquals("no", connected);
	}
	
	@Test
	public void testGetConnectedNewYorkToTorronto() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=NewYork&destination=Torronto", String.class);
		System.out.println("testGetConnectedNewYorkToTorronto  -> "+connected);
		assertNotNull(connected);
		assertEquals("no", connected);
	}
	
	@Test
	public void testGetConnectedBostonToBangalore() {
		String connected = restTemplate.getForObject(getRootUrl() + "/connected?origin=Boston&destination=Bangalore", String.class);
		System.out.println("testGetConnectedBostonToBangalore  -> "+connected);
		assertNotNull(connected);
		assertEquals("no", connected);
	}


}
