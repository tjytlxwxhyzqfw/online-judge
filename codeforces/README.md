# Codeforces

## 000
Unclassified solutions, I write it for exercise instead of contests.

---

*2016-11-27*

- My new tools `progfeed` and `progconf` are introduced
- I start to use **-D** option of `g++`

As a result of above changes, my c++ template now looks like:

```C
int main(void) {
    #ifdef DEBUG
	//freopen("Inputs/B", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	while (read()) {
		solve();
	}

	return 0;
}
```
