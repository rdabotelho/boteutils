package com.m2r.extractor;

import com.m2r.extractor.imp.FaturaNet;

public class Main {

	public static void main(String[] args) {

		ExtractorsSession session = new ExtractorsSession();
		session.addExtractor(new FaturaNet());

		session.run();

	}

}
