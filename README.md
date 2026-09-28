# ArcadiaMedia

ArcadiaMedia is a unified discovery and recommendation backend service built with Java and Spring Boot that tracks movies, TV shows, and anime in a single PostgreSQL database.

---

## What Has Been Implemented (Stage 1 - Baseline)

- **Domain Models (`com.arcadia.model`)**:
  - `MediaItem`: Represents movies, TV shows, or anime with title, original title, overview, ratings, episodes, seasons, and release dates.
  - `Genre`: Many-to-many relationship with media items.
  - `MediaType`: Discriminator enum (`MOVIE`, `TV`, `ANIME`).
- **Database Schema**:
  - Auto-generated PostgreSQL tables: `genres`, `media_items`, and `media_genres`.
  - Custom B-tree indexes on `media_type`, `popularity`, and `vote_average`.
  - Composite unique constraint on `(external_id, external_source)` to prevent duplicate ingestion.
- **Persistence (`com.arcadia.repository`)**:
  - `GenreRepository`: Look up genres by name with case-insensitive matching.
  - `MediaItemRepository`: Pagination support, media type filtering, and custom JPQL collection queries by genre names.
- **Data Initializer (`DataInitializer.java`)**:
  - Non-destructive startup runner that inserts a baseline seed record if the database is empty.

---

## Tech Stack Used

- **Language**: Java 25 (OpenJDK 25.0.4.1)
- **Framework**: Spring Boot 3.3.4 (Spring Web, Spring Data JPA)
- **Database**: PostgreSQL 18.6
- **Build Tool**: Apache Maven 3.9+
- **OS**: Fedora Linux

---
