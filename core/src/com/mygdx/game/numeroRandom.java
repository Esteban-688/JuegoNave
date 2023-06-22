package com.mygdx.game;

import java.util.Random;

public class numeroRandom {
	 public static int generateRandomNumber() {
	        Random random = new Random();
	        int randomNumber = random.nextInt(2) + 1;
	        return randomNumber;
	    }
}
