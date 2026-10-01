package com.arcadia;

import com.arcadia.client.AniListClient;
import com.arcadia.client.dto.AniListDtos.AniListMedia;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AniListClient aniListClient;

    public DataInitializer(AniListClient aniListClient) {
        this.aniListClient = aniListClient;
    }

    @Override
    public void run(String... args) {
        System.out.println("=================================================");
        System.out.println(">>> TESTING ANILIST CLIENT: Fetching Top 5 Anime...");
        System.out.println("=================================================");

        try {
            List<AniListMedia> animeList = aniListClient.fetchPopularAnime(1, 5);

            for (AniListMedia anime : animeList) {
                String title = anime.title().english() != null ? anime.title().english() : anime.title().romaji();
                System.out.println("• [" + anime.id() + "] " + title + " | Score: " + anime.averageScore() + " | Genres: " + anime.genres());
            }

            System.out.println("=================================================");
            System.out.println(">>> ANILIST CLIENT TEST PASSED!");
            System.out.println("=================================================");
        } catch (Exception e) {
            System.err.println(">>> Failed to fetch data from AniList: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
