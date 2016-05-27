package com.m2r.extractor.utils;

public class Pair implements Comparable<Pair> {

	private int pixel;
	private int count;

	public Pair(int pixel, int count) {
		this.pixel = pixel;
		this.count = count;
	}

	public int getPixel() {
		return pixel;
	}

	public void setPixel(int pixel) {
		this.pixel = pixel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pixel;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (pixel != other.pixel)
			return false;
		return true;
	}

	public int compareTo(Pair o) {
		return o.getCount() - this.getCount();
	}

}
