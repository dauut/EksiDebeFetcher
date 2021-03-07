# EksiDebeFetcher

> eksisozluk.con is one of the biggest social media websites in Turkey. It publishes the best posts of the prior's day 
for 24 hours. After this period these posts vanish.

This background service fetches the DEBE (Best posts of yesterday) daily and create records on PostgreSQL table.
It produces the data that can be fetched via [eksidebeAPI Service](https://github.com/dauut/eksidebeAPI).
### This is the *1st* service of an experimental `microservice` architecture.


## Requirements 
- Spring Boot
- PostgreSQL
- Java 8+
- Python3
- Jsoup

## Project Structure
```
.EksiDebeFetcher/                   # eksisozluk.com best posts fetcher
├── scripts                         # python scripts 
├── src                             # Source files 
|    ├── main
|    |    ├── java                
|    |    |    ├── dao                       # Data Access Object Pattern
|    |    |    ├── model                     # data models, objects
|    |    |    ├── service                   # main fetcher services
|    |    |    ├── utils                     # utility classes, configurations
|    |    ├── resources              # log4j, application.properties
├── logs                             # logs
└── README.md
```