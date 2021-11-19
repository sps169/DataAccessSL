# MariaDB
FROM yobasystems/alpine-mariadb:10
# FROM mariadb:10.5

# Configuramos BBDD
ENV MYSQL_ROOT_PASSWORD mariadb
ENV MYSQL_USER mariadb
ENV MYSQL_PASSWORD mariadb
ENV MYSQL_DATABASE data_access_sl

# Copiamos los ficheros sql para que se ejecuten
COPY ./sql /docker-entrypoint-initdb.d/