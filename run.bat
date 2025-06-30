@echo on
echo 編譯中...
cd src
javac -d ../bin -cp .;../lib/mysql-connector-j-9.3.0.jar *.java
cd ..
echo 執行程式...
java -cp bin;lib/mysql-connector-j-9.3.0.jar Main
pause
