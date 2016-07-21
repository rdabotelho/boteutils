package com.m2r.ksutils.utils;

import java.security.Principal;

public class SubjectDN {

	private static final String COMMON_NAME = "CN";

	private static final String ORGANIZATIONAL_UNIT = "OU";

	private static final String ORGANIZATION = "O";

	private static final String LOCALITY = "L";

	private static final String STATE_OR_PROVINCE_NAME = "ST";

	private static final String COUNTRY_NAME = "C";

	private String commonName;

	private StringBuilder organizationalUnit;

	private String organization;

	private String locality;

	private String stateOrProvinceName;

	private String countryName;

	private SubjectDN(Principal subject) {
		String attributes[] = subject.toString().split(",");
		this.organizationalUnit = new StringBuilder();
		for (String s : attributes) {
			String[] p = s.split("=");
			switch (p[0].trim()) {
				case COMMON_NAME:
					this.commonName = p[1];
					break;
				case ORGANIZATIONAL_UNIT:
					this.organizationalUnit.append(p[1]).append(",");
					break;
				case ORGANIZATION:
					this.organization = p[1];
					break;
				case LOCALITY:
					this.locality = p[1];
					break;
				case STATE_OR_PROVINCE_NAME:
					this.stateOrProvinceName = p[1];
					break;
				case COUNTRY_NAME:
					this.countryName = p[1];
			}
		}
		if (this.organizationalUnit.length() > 0) {
			this.organizationalUnit.delete(this.organizationalUnit.length() - 1, this.organizationalUnit.length());
		}
	}

	public static SubjectDN getInstance(Principal subject) {

		return new SubjectDN(subject);
	}

	public String getCommonName() {

		return this.commonName;
	}

	public String getOrganizationalUnit() {

		return this.organizationalUnit.toString();
	}

	public String getOrganization() {

		return this.organization;
	}

	public String getLocality() {

		return this.locality;
	}

	public String getStateOrProvinceName() {

		return this.stateOrProvinceName;
	}

	public String getCountryName() {

		return this.countryName;
	}

}
