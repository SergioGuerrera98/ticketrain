package com.corso.ticketrain.checkstring;

public class Levenshtein extends CheckString{
	
	@Override
	public boolean check(String s1, String s2) {
		System.out.println("algoritmo di Levenshtein");
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
                }
            }
        }

        int maxLen = Math.max(s1.length(), s2.length());
        double similarity = 1.0 - ((double) dp[s1.length()][s2.length()] / maxLen);

        // You can set a threshold for similarity based on your requirements
        double threshold = 0.7;

        return similarity >= threshold;
	}
}
