package main

func longestPalindrome(s string) string {
	if len(s) < 2 {
		return s
	}
	dp := make([]int, len(s))
	dp[0] = 1
	var result string
	for i := 1; i < len(s); i++ {
		dp[i] = 1
		if dp[i-1] == dp[i] {
			dp[i]++
		}
		if j := i - dp[i-1] - 1; j >= 0 && s[j] == s[i] {
			dp[i] = 2 + dp[i-1]
		}
		if len(result) < dp[i] {
			result = s[i+1-dp[i]:i+1]
		}
	}
	return result
}

func main() {
	assert(longestPalindrome("") == "")
	assert(longestPalindrome("a") == "a")
	assert(longestPalindrome("aa") == "aa")
	assert(longestPalindrome("aaa") == "aaa") // fails
	assert(longestPalindrome("aaaa") == "aaaa")
	assert(longestPalindrome("aaaaa") == "aaaaa")
}

func assert(x bool) {
	if !x {
		panic("assertion failed")
	}
}
