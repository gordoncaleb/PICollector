package com.gordoncaleb.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;

public class CSVDeserializer {
	

	public static List<PhysicalAddress> readCSVAddressFile(String fileName) {

		List<PhysicalAddress> addresses = new ArrayList<PhysicalAddress>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String colNameLine = br.readLine();

			if (colNameLine != null) {

				String line;

				while ((line = br.readLine()) != null) {
					addresses.add(parseAddress(line));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return addresses;
	}

	public static PhysicalAddress parseAddress(String line) {
		PhysicalAddress address = new PhysicalAddress();

		String[] tokens = line.split(",");

		address.setFipsCode(getToken(tokens, 0));
		address.setUnformattedApn(getToken(tokens, 1));
		address.setHouseNumber(getToken(tokens, 2));
		address.setStreetName(getToken(tokens, 3));
		address.setMode(getToken(tokens, 4));
		address.setCity(getToken(tokens, 5));
		address.setState(getToken(tokens, 6));

		return address;
	}

	public static String getToken(String[] tokens, int index) {
		if (tokens != null && tokens.length > index) {
			return tokens[index];
		} else {
			return null;
		}
	}
}
