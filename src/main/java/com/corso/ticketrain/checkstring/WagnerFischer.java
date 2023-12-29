package com.corso.ticketrain.checkstring;

public class WagnerFischer extends CheckString{

	@Override
	public boolean check(String s1, String s2) {
		System.out.println("algoritmo di WagnerFischer");
		int m = s1.length();
		int n = s2.length();

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {
					int substitutionCost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
					dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + substitutionCost);
				}
			}
		}
		if (dp[m][n] <= 3) {
			return true;
		}else {
			return false;
		}
	}

}
