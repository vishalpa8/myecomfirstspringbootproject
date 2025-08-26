@echo off
title Windows Cleanup Script

echo ===== User Temp Files =====
del /f /s /q "%TEMP%\*.*"
for /d %%x in ("%TEMP%\*") do rd /s /q "%%x"

echo.
echo ===== Local Temp Files =====
del /f /s /q "%USERPROFILE%\AppData\Local\Temp\*.*"
for /d %%x in ("%USERPROFILE%\AppData\Local\Temp\*") do rd /s /q "%%x"

echo.
echo ===== Clearing Recent Items =====
del /f /q "%APPDATA%\Microsoft\Windows\Recent\*"

echo.
echo ===== Browser Cache Cleanup =====
:: Chrome
del /f /s /q "%LOCALAPPDATA%\Google\Chrome\User Data\Default\Cache\*.*"
del /f /s /q "%LOCALAPPDATA%\Google\Chrome\User Data\Default\Code Cache\*.*"

:: Edge
del /f /s /q "%LOCALAPPDATA%\Microsoft\Edge\User Data\Default\Cache\*.*"
del /f /s /q "%LOCALAPPDATA%\Microsoft\Edge\User Data\Default\Code Cache\*.*"

@echo off
echo ===== Network Cleanup =====

echo.
echo Releasing IP address (may fail without admin)...
ipconfig /release

echo Renewing IP address
ipconfig /renew

echo Flushing DNS cache
ipconfig /flushdns

echo.
echo Resetting Winsock (may require reboot)...
netsh winsock reset

echo Resetting TCP/IP stack (may require reboot)...
netsh int ip reset 

echo.
echo ===== Netstat Diagnostics =====
echo Running netstat -n...
netstat -n

echo Running netstat -r...
netstat -r


echo.
echo ===== Done! System cleaned (as much as allowed without admin) =====
pause
