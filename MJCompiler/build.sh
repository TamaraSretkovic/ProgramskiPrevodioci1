#!/bin/sh

red='\x1B[0;31m'
brown='\x1B[0;33m'
NC='\x1B[0m' # No Color

echo
echo  ${brown} LEXER ${red}
echo

java -jar lib/JFlex.jar -d src/rs/ac/bg/etf/pp1/ spec/mjlexer.lex

echo
echo  ${brown} PARSER ${red}
echo

java -jar lib/cup_v10k.jar -destdir src/rs/ac/bg/etf/pp1/ -ast src.rs.ac.bg.etf.pp1.ast  -parser MJParser -buildtree spec/mjparser.cup
sed -i 's/src.rs.ac.bg.etf.pp1.ast/rs.ac.bg.etf.pp1.ast/g' src/rs/ac/bg/etf/pp1/ast/*

echo ${NC}


echo
echo  ${brown} Compiling with javac ${red}
echo

javac -cp .:src:config:lib/cup_v10k.jar:lib/mj-runtime-1.1.jar:lib/symboltable-1-1.jar:lib/log4j-1.2.17.jar:lib/JFlex.jar -Xdiags:verbose src/rs/ac/bg/etf/pp1/*.java src/rs/ac/bg/etf/pp1/util/*.java test/rs/ac/bg/etf/pp1/*.java
