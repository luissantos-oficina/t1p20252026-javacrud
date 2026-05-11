#!/bin/bash

DATE=$(date +%Y-%m-%d_%H-%M-%S)
BACKUP_DIR="./backups/mysql"
DB_NAME="appdb"

mkdir -p $BACKUP_DIR

docker exec mysql-db mysqldump -u root -proot  $DB_NAME \
> $BACKUP_DIR/${DB_NAME}_$DATE.sql

# opcional: limpar backups antigos
find $BACKUP_DIR -type f -mtime +7 -delete
