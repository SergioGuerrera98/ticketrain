package com.corso.ticketrain.checkstring;

public class HuntSzymanski extends CheckString{

	@Override
	public boolean check(String s1, String s2) {
		System.out.println("algoritmo di HuntSzymanski");
		int n = s1.length();
		int m = s2.length();

		// Parametri dell'algoritmo
		int k = 5; // Lunghezza della sottostringa
		int maxErrors = 3; // Numero massimo di errori ammessi

		for (int i = 0; i <= n - k; i++) {
			String substring1 = s1.substring(i, i + k);

			for (int j = 0; j <= m - k; j++) {
				String substring2 = s2.substring(j, j + k);

				// Conta gli errori di corrispondenza
				int errors = countErrors(substring1, substring2);

				// Verifica se il numero di errori è al di sotto della soglia massima
				if (errors <= maxErrors) {
					return true;
				}
			}
		}

		return false;
	}

	private static int countErrors(String s1, String s2) {
		int errors = 0;

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				errors++;
			}
		}

		return errors;
	}
}
