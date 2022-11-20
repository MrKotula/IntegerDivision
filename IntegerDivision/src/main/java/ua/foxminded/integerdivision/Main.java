package ua.foxminded.integerdivision;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {

		int dividend;
		int divisor;
		String result;
		Scanner scan = new Scanner(System.in);
		
		logger.info("input dividend number: ");
		dividend = scan.nextInt();
		logger.info("input divisor number: ");
		divisor = scan.nextInt();
		result = new Division().division(dividend, divisor);
		logger.info(result);
		scan.close();

	}

}