@echo off
cd /d "%~dp0"
if not exist out mkdir out
dir /s /b src\*.java > fontes.txt
javac -encoding UTF-8 -d out @fontes.txt
if errorlevel 1 (pause & exit /b 1)
java -cp out br.com.bairroemdia.Main
pause
