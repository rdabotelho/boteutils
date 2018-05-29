package com.m2r.scaffoldingtest.model;

import java.io.Serializable;

public interface BaseModel<ID extends Serializable> extends Serializable {
	
	public abstract ID getId();
	
}
