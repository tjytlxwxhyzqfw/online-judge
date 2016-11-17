import os
import subprocess

try:
	with open("1075.old","r") as inf, open("1075","w") as outf:
		for line in inf:
			if not (("START" in line)or("END" in line)):
				outf.write(line.lower())
				print( line+" transformed: "+line.lower() )
			else:
				outf.write(line)
except IOError as ioe:
	print( str(ioe) )
	pass
