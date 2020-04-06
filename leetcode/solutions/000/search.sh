#! /bin/bash

for i in `find .. -regex .*\.java`; do
	echo $i
	cat $i | grep --color -i $1
done
