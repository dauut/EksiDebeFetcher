# EksiDebeFetcher

> eksisozluk.con is one of the biggest social media websites in Turkey. It publishes the best posts of the prior's day 
for 24 hours. After this period these posts vanish.
>DEBE (Best posts of yesterday)

This background service fetches best posts daily and create records on PostgreSQL table.

#### Requirements 
- Spring Boot
- PostgreSQL
- Java 8+

```
.EksiDebeFetcher/                   # eksisozluk.com best posts fetcher
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