package com.m2r.flatfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.m2r.flatfile.annotation.FlatField;
import com.m2r.flatfile.enumeration.FlatFieldTypeEnum;
import com.m2r.flatfile.exception.FlatFileException;

public class FlatFile {

	private BufferedReader buffer;
	private String line;
	private boolean isEnd;

	private List<Class<?>> flatFields;

	public FlatFile() {
		this.flatFields = new ArrayList<Class<?>>();
	}

	public void registerRecord(Class<?> flatField) {

		this.flatFields.add(flatField);
	}

	public Iterator<Object> load(Reader reader) throws FlatFileException {

		this.close();
		this.isEnd = false;
		this.line = null;
		this.buffer = new BufferedReader(reader);
		this.readLine();

		return new FlatFileIterator(this);
	}

	protected void readLine() throws FlatFileException {
		try {
			this.line = this.buffer.readLine();
		} catch (Exception e) {
			throw new FlatFileException(e);
		}
	}

	protected boolean hasNextRecord() {
		if (this.line == null) {
			if (!this.isEnd) {
				this.close();
				this.isEnd = true;
			}
		}
		return !this.isEnd;
	}

	protected Object nextRecord() throws Exception {
		Class<?> clazz = this.getFlatFieldClass();
		Object object = null;
		if (clazz != null) {
			object = this.readFlatFileObject(clazz);
		}
		this.readLine();
		return object;
	}

	protected void close() {
		try {
			if (this.buffer != null) {
				this.buffer.close();
				this.buffer = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Class<?> getFlatFieldClass() {

		FlatField ff = null;
		String id = null;
		for (Class<?> clazz : this.flatFields) {
			for (Field field : clazz.getDeclaredFields()) {
				ff = field.getAnnotation(FlatField.class);
				if ((ff != null) && !ff.id().equals("")) {
					id = this.line.substring(ff.begin(), ff.end());
					if (ff.id().endsWith(id)) {
						return clazz;
					}
					break;
				}
			}
		}
		return null;
	}

	private Object readFlatFileObject(Class<?> clazz) throws InstantiationException, IllegalAccessException {

		FlatField ff = null;
		FlatFieldTypeEnum ffe = null;
		Object value = null;
		Object object = clazz.newInstance();
		for (Field field : clazz.getDeclaredFields()) {
			ff = field.getAnnotation(FlatField.class);
			ffe = ff.converter();
			if (ff != null) {
				value = ffe.convert(field.getType(), this.line.substring(ff.begin(), ff.end()));
				field.setAccessible(true);
				field.set(object, value);
				field.setAccessible(false);
			}
		}
		return object;
	}

}
