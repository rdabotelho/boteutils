package com.m2r.extractor.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageFilter {

	private static final int BRANCO = Color.WHITE.getRGB();

	public static File imageCleam(File imgFile) throws IOException {
		BufferedImage image = ImageIO.read(imgFile);

		// estatistica
		List<Pair> pixelsList = new LinkedList<Pair>();
		Map<Integer, Pair> pixelsMap = new HashMap<Integer, Pair>();
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Integer pixel = image.getRGB(x, y);
				Pair pair = pixelsMap.get(pixel);
				if (pair == null) {
					pair = new Pair(pixel, 0);
					pixelsList.add(pair);
					pixelsMap.put(pixel, pair);
				}
				pair.setCount(pair.getCount() + 1);
			}
		}

		Collections.sort(pixelsList);

		// obtem os 2 pixels com  maior ocorrencia
		int countMore = 5;
		Map<Integer, Integer> mapVal = new HashMap<Integer, Integer>();
		for (int i = 0; i < countMore; i++) {
			Pair pair = pixelsList.get(i);
			mapVal.put(pair.getPixel(), pair.getCount());
		}

		// reduzir pixels de acordo com o map
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Integer pixel = image.getRGB(x, y);
				if (mapVal.containsKey(pixel)) {
					image.setRGB(x, y, pixel);
				} else {
					image.setRGB(x, y, BRANCO);
				}
			}
		}

		// tirar ilhas 3x
		eliminateIslandX(image);
		eliminateIslandY(image);
        
		File imgCleanned = new File(imgFile.getParentFile(), "C" + imgFile.getName());
		ImageIO.write(image, "png", imgCleanned);
		
		return imgCleanned;
	}

	private static void eliminateIslandX(BufferedImage image) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (isNextPixelXDifferent(image, x, y)) {
					image.setRGB(x, y, BRANCO);
				}
			}
		}		
	}	
	
	private static void eliminateIslandY(BufferedImage image) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (isNextPixelYDifferent(image, x, y)) {
					image.setRGB(x, y, BRANCO);
				}
			}
		}		
	}	
	
	private static boolean isNextPixelXDifferent(BufferedImage image, int x, int y) {
		boolean dif = true;
		if (x < (image.getWidth() - 1)) {
			dif = image.getRGB(x + 1, y) != image.getRGB(x, y);
		}
		return dif;
	}
	
	private static boolean isNextPixelYDifferent(BufferedImage image, int x, int y) {
		boolean dif = true;
		if (y < (image.getHeight() - 1)) {
			dif = image.getRGB(x, y + 1) != image.getRGB(x, y);
		}
		return dif;
	}
	
}
