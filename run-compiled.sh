#!/usr/bin/env bash

export DATABASE_URL=jdbc:postgresql://localhost:5432/postgres
export DATABASE_USERNAME=postgres
export DATABASE_PASSWORD=postgres

java -jar backend.jar
