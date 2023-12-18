FROM postgres:15

RUN apt update
RUN apt install wget -y
RUN apt install postgresql-15-postgis-3 -y
RUN apt install postgis -y

ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=employee-manager

COPY initial_schema.sql /docker-entrypoint-initdb.d/

EXPOSE 5432