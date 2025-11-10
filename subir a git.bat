@echo off
REM ===========================================
REM  PUSH AUTOMÁTICO A GIT
REM  Autor: Juanjo
REM ===========================================

REM --- Cambia a la carpeta de tu proyecto ---
cd /d "C:\biblioteca02"

REM --- Muestra el estado actual ---
echo -------------------------------------------
echo   Actualizando repositorio...
echo -------------------------------------------
git status

REM --- Agrega todos los cambios ---
git add .

REM --- Crea un commit con fecha/hora ---
set FECHA=%date% %time%
git commit -m "Commit automático - %FECHA%"

REM --- Hace push a la rama actual ---
git push origin HEAD

echo -------------------------------------------
echo   ✅ Push completado correctamente.
echo -------------------------------------------
pause
