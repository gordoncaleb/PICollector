package com.gordoncaleb.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;

public class CSVDeserializerTest {

	@Test
	public void shouldParse() {

		String line = "37129,R01000001011000,4924,INDIAN CORN,TRL,CASTLE HAYNE,NC";

		PhysicalAddress testAddress = CSVDeserializer.parseAddress(line);

		assertTrue(testAddress.getFipsCode().equalsIgnoreCase("37129"));
		assertTrue(testAddress.getUnformattedApn().equalsIgnoreCase(
				"R01000001011000"));
		assertTrue(testAddress.getHouseNumber().equalsIgnoreCase("4924"));
		assertTrue(testAddress.getStreetName().equalsIgnoreCase("INDIAN CORN"));
		assertTrue(testAddress.getMode().equalsIgnoreCase("TRL"));
		assertTrue(testAddress.getCity().equalsIgnoreCase("CASTLE HAYNE"));
		assertTrue(testAddress.getState().equalsIgnoreCase("NC"));
	}

	@Test
	public void shouldGetToken() {

		String[] tokens = new String[] { "37129", "R01000001011000", "4924",
				"INDIAN CORN", "TRL", "CASTLE HAYNE", "NC" };

		assertTrue(CSVDeserializer.getToken(tokens, 0).equalsIgnoreCase(
				tokens[0]));
		assertTrue(CSVDeserializer.getToken(tokens, 1).equalsIgnoreCase(
				tokens[1]));
		assertTrue(CSVDeserializer.getToken(tokens, 2).equalsIgnoreCase(
				tokens[2]));
		assertTrue(CSVDeserializer.getToken(tokens, 3).equalsIgnoreCase(
				tokens[3]));
		assertTrue(CSVDeserializer.getToken(tokens, 4).equalsIgnoreCase(
				tokens[4]));
		assertTrue(CSVDeserializer.getToken(tokens, 5).equalsIgnoreCase(
				tokens[5]));
		assertTrue(CSVDeserializer.getToken(tokens, 6).equalsIgnoreCase(
				tokens[6]));

		assertTrue(CSVDeserializer.getToken(tokens, 7) == null);
	}
}
