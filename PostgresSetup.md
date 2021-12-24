## Start / stop postgres

```shell
$ brew services start postgresql
$ brew services stop postgresql
```

## Postgres presetup

```shell
$ sudo psql -U andreyminin

andreyminin=# create database catalizator;
andreyminin=# create user andrey with encrypted password '123';
andreyminin=# grant all privileges on database catalizator to andrey;
andreyminin=# \q
```
