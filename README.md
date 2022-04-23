# DAO Library

Library book borrowing software that doesn't care about time.

Thanks for the book data from [The Greatest Book](https://thegreatestbooks.org/).

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

