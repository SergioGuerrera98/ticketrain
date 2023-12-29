package com.corso.ticketrain.checkstring;

public class JaroWinkler extends CheckString{

	@Override
	public boolean check(String s1, String s2) {
		System.out.println("algoritmo di JaroWinkler");
		double threshold = 0.7; // You can adjust this threshold based on your requirements
		double p = 0.1; // Winkler's constant

		int commonPrefixLength = 0;

		// Find the length of the common prefix
		int prefixLength = Math.min(4, Math.min(s1.length(), s2.length()));
		for (int i = 0; i < prefixLength; i++) {
			if (s1.charAt(i) == s2.charAt(i)) {
				commonPrefixLength++;
			} else {
				break;
			}
		}

		// Jaro similarity
		int matchCount = 0;
		int transpositions = 0;

		for (int i = 0; i < s1.length(); i++) {
			for (int j = Math.max(0, i - 4); j < Math.min(i + 4, s2.length()); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					matchCount++;
					if (i != j) {
						transpositions++;
					}
					break;
				}
			}
		}

		// Calculate Jaro similarity
		double jaroSimilarity = (matchCount > 0) ? ((double) matchCount / s1.length() +
				(double) matchCount / s2.length() + (double) (matchCount - transpositions) / matchCount) / 3.0 : 0.0;

		// Calculate Jaro-Winkler similarity
		double jaroWinklerSimilarity = jaroSimilarity + (commonPrefixLength * p * (1 - jaroSimilarity));

		return jaroWinklerSimilarity >= threshold;
	}

}
