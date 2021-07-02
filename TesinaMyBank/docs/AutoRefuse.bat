@echo off
echo sono qui
echo %cd%
java -cp %cd%\AutoRefuseExternalTransaction.jar it.exolab.tesina.mybank.bat.auto.refuse.external.transaction.AutoRefuseExternalTransaction "jdbc:mysql://localhost:3306/tesina_mybank" "root" "gigingongo"