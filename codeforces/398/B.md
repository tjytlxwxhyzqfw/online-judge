# 心得和体会

1. continue? break ?
2. 只要出现long long的题目, 一定要抓取int来做检查!切记!切记!!!

### 2017-02-18 14:04:33
**WA on test3**

忘记了特殊情况: 当客户到达的时候, 工作人员即将下班但是还没下班.  
也就是说, 工作人员将与9点下班, 服务一个人需要10分钟, 但是客户8点59来的, 这就尴尬了...  
题目已经说了, 在这种情况下, 拒绝提供服务:
If the receptionist would stop working within t minutes, he stops serving visitors (other than the one he already serves).  
当时这句话没读懂, 遗憾

修改代码:

```python
if clk + wait + t > tf: #+t
	break
```

### 2017-02-19 13:44:12
**Wrong answer on test 4**

思维漏洞: 当客户到来的时候, 如果(clk+wait+t>tf), 那么真的应该停止遍历剩下的所有候选时间吗?  
显然不是!!!  
这个候选时间超时, 并不意味着所有的候选时间都超时啊!!!  

**一定要在break和continue之间慎重选择!!!**

### 2017-02-19 13:49:32
**Runtime error on test 9**

输入判定: n可能等于0!!!

### 2017-02-19 14:03:10
**Time limit exceeded on test 10**

换用C++

### 2017-02-19 15:31:17
**Wrong answer on test 10**

又是溢出问题,   
形式参数声明为int, 而实际参数是long long  





