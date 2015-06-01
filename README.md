TODO App
========

Zdrojový kód TODO aplikace pro Android.


Databáze
========

- Používá se ORM knihovna [ActiveAndroid](https://github.com/pardom/ActiveAndroid), protože je velmi jednoduchá na použití
- V rámci zjednodušení se nepracuje s _AsyncTask_


Návod na použití databáze
-------------------------

- Zkopírovat do projektu celý package "database"
- Upravit ve všech třídách package name
- Přidat do _/app/build.gradle_ knihovnu:

```groovy
dependencies {
	...
	compile 'com.michaelpardo:activeandroid:3.1.0-SNAPSHOT'
}
```

- Přidat do _AndroidManifest.xml_ dovnitř _application_ elementu meta data:

```xml
<meta-data android:name="AA_DB_NAME" android:value="todo.db" />
<meta-data android:name="AA_DB_VERSION" android:value="1" />
```

- Vytvořit _TodoApplication.java_ třídu, kde se v _onCreate_ metodě inicializuje databáze:

```java
ActiveAndroid.initialize(this);
```

- Application třídu je potřeba deklarovat v _AndroidManifest.xml_ a v atributu _name_ uvést cestu k třídě:

```xml
<application android:name=".TodoApplication" ... />
```


Práce s databází
----------------

- Vytvoříme is DAO objekt:

```java
TaskDAO dao = new TaskDAO();
```

- Voláme metody: _create_, _read_, _readFirst_, _readAll_, _update_, _delete_, _deleteAll_
- Třída _TaskDAO.java_ pracuje navenek s _TaskEntity.java_
