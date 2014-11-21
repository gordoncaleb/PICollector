package com.gordoncaleb.domain.pi.contact;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.gordoncaleb.geocoding.google.GoogleGeoCode;

public class PhysicalAddressTest {

	@Test
	public void testGenerateAddressFromFormattedAddress() {
		PhysicalAddress addr = new PhysicalAddress();

		GoogleGeoCode geoCode = new GoogleGeoCode();
		geoCode.setFormattedAddress("1234 Sea Aire, Supply, NC 28462, USA");
		addr.setGeoCode(Arrays.asList(geoCode));

		PhysicalAddress newAddr = addr.generateAddressFromFormattedAddress();

		System.out.println(newAddr);
	}

}
