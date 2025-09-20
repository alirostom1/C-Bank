# Gestion de comptes bancaires - Java 8 (Console)

## Description du projet
Application console en Java 8 permettant de gérer les comptes bancaires (courant et épargne) et leurs opérations (versements, retraits, virements).

## Technologies utilisées
- Java 8 (JDK 1.8)
- JDBC mysql
- Java Time API
- UUID

## Structure du projet
- `src/com/cbank/model` : classes métier (Compte, CompteCourant, CompteEpargne, Operation, Versement, Retrait)
- `src/com/cbank/service` : logique métier (BankService)
- `src/com/cbank/ui` : interface console (ConsoleUI)
- `src/com/cbank/util` : validations (Validator)
- `conception/C-Bank.png` : diagramme de classes
- `src/run.sh` : scripts de compilation
- `dist/bank-app.jar` : exécutable

## Prérequis
- JDK 8 installé
- Git

## Diagramme de classe
![class diagram](https://github.com/alirostom1/C-Bank/blob/main/conception/C-Bank.png)

## Execeute the program
```bash
java -cp "dist/cbank.jar:src/io/github/alirostom1/cbank/drivers/mysql-connector-j-9.4.0.jar" io.github.alirostom1.cbank.Main 

