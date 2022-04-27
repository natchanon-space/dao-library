# DAO Library

Library book borrowing software that doesn't care about time.

This library has no limit amount of each book, so it will keep only borrowed/returned status only.

Thanks for the book data from [The Greatest Book](https://thegreatestbooks.org/).

## Requirements
We need `Java 17`. In my case, I use `openjdk17`
.

Edit: I have tested on `Java 11` using `corretto-11`, it works just fine.

## Initialize Database
For the Window,
```
# create the database using specified schema
sqlite3 library.db -init library.schema

# import data from csv files
sqlite3> .mode csv
sqlite3> .import --skip 1 data/book.csv book
sqlite3> .import --skip 1 data/member.csv member
sqlite3> .import --skip 1 data/borrow.csv borrow
```

## Package Diagram

![package diagram](img/package-diagram.drawio.png)

## Class Diagrams

### Library Package

![library class diagram](img/class-diagram.drawio.png)

### GUI Package

![gui class diagram](img/gui-diagram.drawio.png)
