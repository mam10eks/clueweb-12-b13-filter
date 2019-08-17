#!/bin/bash -e

EXPECTED_MD5SUM="188d7c1ef9da22bfdbb6e2b2b474d312  src/main/resources/clueweb-12-b13-ids.txt"
ACTUAL_MD5SUM="$(md5sum src/main/resources/clueweb-12-b13-ids.txt || echo 'not-there!')"

if [ "${EXPECTED_MD5SUM}" = "${ACTUAL_MD5SUM}" ]
then
	echo "The resource 'src/main/resources/clueweb-12-b13-ids.txt' is already there!"
	exit 0
fi

mkdir -p target/tmp
cd target/tmp

wget http://lemurproject.org/clueweb12/ClueWeb12-CreateB13.tgz

tar -zxvf ClueWeb12-CreateB13.tgz

cd ClueWeb12-CreateB13
bzip2 -dk ClueWeb12_B13_DocID_To_URL.txt.bz2

cd ../../..

cat target/tmp/ClueWeb12-CreateB13/ClueWeb12_B13_DocID_To_URL.txt |\
	awk -F ',' '/1/ {print $1}'|
       	sort -u >\
       	src/main/resources/clueweb-12-b13-ids.txt

rm -R target/tmp



echo "Done ;)"
