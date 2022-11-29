package ua.foxminded.integerdivision;

import static java.lang.Math.abs;

public class Division {
	private static final StringBuilder RESULT = new StringBuilder();
	private static final StringBuilder QUOTIENT = new StringBuilder();
	private static final StringBuilder REMINDER = new StringBuilder();
	private static final StringBuilder NEXT_TERM = new StringBuilder("\n");
	private static final char NEXT_TERM_CHAR = '\n';
	
	public String division(int dividend, int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor cannot be 0, division by zero");
		}

		int positiveDividend = abs(dividend);
		int posotiveDivisor = abs(divisor);

		if (positiveDividend < posotiveDivisor) {
			return "" + positiveDividend + "/" + posotiveDivisor + "=0";
		}

		String[] digits = String.valueOf(positiveDividend).split("");
		int reminderNumber;
		int multiplyResult;
		int divisorDigit = calculateDigit(posotiveDivisor);
		int modularFunction;

		for (int i = 0; i < digits.length; i++) {
			REMINDER.append(digits[i]);
			reminderNumber = Integer.parseInt(REMINDER.toString());

			if (reminderNumber >= posotiveDivisor) {
				modularFunction = reminderNumber % posotiveDivisor;
				multiplyResult = reminderNumber / posotiveDivisor * posotiveDivisor;
				
				String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber);
				RESULT.append(lastReminder).append(NEXT_TERM);

				String multiply = String.format("%" + (i + 2) + "d", multiplyResult);
				RESULT.append(multiply).append(NEXT_TERM);

				Integer tab = lastReminder.length() - calculateDigit(multiplyResult);
				RESULT.append(makeDivider(reminderNumber, tab)).append(NEXT_TERM);

				QUOTIENT.append(reminderNumber / posotiveDivisor);

				REMINDER.replace(0, REMINDER.length(), String.valueOf(modularFunction));
				reminderNumber = Integer.parseInt(REMINDER.toString());
			} else {
				if (i >= divisorDigit) {
					QUOTIENT.append(0);
				}
			}

			if (i == digits.length - 1) {
				RESULT.append(String.format("%" + (i + 2) + "s", reminderNumber)).append(NEXT_TERM);
			}
		}
		modifyResultToView(positiveDividend, posotiveDivisor);
		return RESULT.toString();
	}

	private String makeDivider(Integer reminderNumber, Integer tab) {
		return assemblyString(tab, ' ') + assemblyString(calculateDigit(reminderNumber), '-');
	}

	private void modifyResultToView(Integer dividend, Integer divisor) {
		int[] index = new int[3];
		int i = 0;
		int j = 0;
		while (i < RESULT.length()) {
			i++;
			if (RESULT.charAt(i) == NEXT_TERM_CHAR) {
				index[j] = i;
				j++;
				if (j == 3) {
					break;
				}
			}
		}

		int tab = calculateDigit(dividend) + 1 - index[0];
		RESULT.insert(index[2], assemblyString(tab, ' ') + "|" + QUOTIENT.toString());
		RESULT.insert(index[1], assemblyString(tab, ' ') + "|" + assemblyString(QUOTIENT.length(), '-'));
		RESULT.insert(index[0], "|" + divisor);
		RESULT.replace(1, index[0], dividend.toString());
	}

	private int calculateDigit(int i) {
		return (int) Math.log10(i) + 1;
	}

	private String assemblyString(int numberOfSymbols, char symbol) {
		StringBuilder lineSet = new StringBuilder();
		
		for (int i = 0; i < numberOfSymbols; i++) {
			lineSet.append(symbol);
		}
		
		return lineSet.toString();
	}
}