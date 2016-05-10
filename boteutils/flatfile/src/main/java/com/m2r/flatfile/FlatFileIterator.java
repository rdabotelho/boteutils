package com.m2r.flatfile;

import java.util.Iterator;

public class FlatFileIterator implements Iterator<Object> {

	private FlatFile flatFile;

	public FlatFileIterator(FlatFile flatFile) {
		this.flatFile = flatFile;
	}

	public boolean hasNext() {
		return this.flatFile.hasNextRecord();
	}

	public Object next() {
		try {
			return this.flatFile.nextRecord();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
