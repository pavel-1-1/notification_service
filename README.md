# Service Template

Стандартный шаблон проекта на SpringBoot

# Использованные технологии

* [Spring Boot](https://spring.io/projects/spring-boot) – как основной фрэймворк
* [PostgreSQL](https://www.postgresql.org/) – как основная реляционная база данных
* [Redis](https://redis.io/) – как кэш и очередь сообщений через pub/sub
* [Liquibase](https://www.liquibase.org/) – для ведения миграций схемы БД
* [Gradle](https://gradle.org/) – как система сборки приложения
* [Lombok](https://projectlombok.org/) – для удобной работы с POJO классами
* [MapStruct](https://mapstruct.org/) – для удобного маппинга между POJO классами

# База данных
* Liquibase сам накатывает нужные миграции на голый PostgreSql при старте приложения
* В коде продемонстрирована работа как с JdbcTemplate, так и с JPA (Hibernate)