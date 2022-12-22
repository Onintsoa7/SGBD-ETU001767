# SGBD-ETU001767
--------------------------------------------------------------------
Create user USERNAME=MDP
ex: create user oni=1767
--------------------------------------------------------------------
Users ne peuvent pas etres dropper
--------------------------------------------------------------------
Connect as USERNAME=MDP
ex: connect as oni=1767
--------------------------------------------------------------------
Create database DATABASENAME
ex: create database school
--------------------------------------------------------------------
Drop database DATABASENAME
ex: Drop database school
--------------------------------------------------------------------
Use DATABASENAME
ex: Use school
--------------------------------------------------------------------
SHOW TABLES 
--------------------------------------------------------------------
Create table TABLENAME(ATTRIBU1_type,ATTRIBUT2_type,...)

Type d'attributs : -int 
                   -string

ex: create table primary(school_string,grade_int,age_int)
--------------------------------------------------------------------
Delete table TABLENAME
ex:delete table primary
--------------------------------------------------------------------
Insert into TABLENAME values(value1,value2,...)
Si le type de value = string , utiliser {}
Si le type de value = int , y mettre des chiffres

ex: insert into primary values({ndn},3,12)
--------------------------------------------------------------------
********************************UPDATE******************************
Update TABLENAME set COLUMN = value1 where COLUMN = value2
La colonne de la valeur a changée doit etre meme que celui du where
Si int
ex: update primary set age = 3 where age = 4
Si string
ex: update primary set school = {domi} where school = {ndn}

********************************UPDATE******************************
--------------------------------------------------------------------
********************************SELECT******************************
Select * from TABLENAME

Select * from TABLENAME where CONDITION = VALUE

Select ATTRIBUT from TABLENAME

Select ATTRIBUT1,ATTRIBUT2,... from TABLENAME

Select ATTRIBUT from TABLENAME where CONDITION = VALUE

Select ATTRIBUT1,ATTRIBUT2,... from TABLENAME where CONDITION = VALUE

Select * from TABLENAME not in select * from TABLENAME where CONDITION = VALUE

Select ATTRIBUT1,ATTRIBUT2,... from TABLENAME (where) not in select ATTRIBUT1,ATTRIBUT2,... from TABLENAME (where)

Select product between TABLENAME1 and TABLENAME2

Select * from TABLENAME where COLUMN (<,<=,>=,==,>) VALUE

Select * division TABLENAME1 by TABLENAME2 
********************************SELECT******************************
--------------------------------------------------------------------
To deconnect "ctrl + C"
--------------------------------------------------------------------
-Pour entrer l'IP pour hoster
-Compiler avec 
>>> javac -d . *.java
-Pour lancer le server 
>>> java compile.ServerMain
-Pour lancer un client 
>>> java compile.ClientMain

--------------------------------------------------------------------

