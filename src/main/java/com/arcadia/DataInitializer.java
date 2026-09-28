package com.arcadia;

import com.arcadia.model.Genre;
import com.arcadia.model.MediaItem;
import com.arcadia.model.MediaType;
import com.arcadia.repository.GenreRepository;
import com.arcadia.repository.MediaItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MediaItemRepository mediaItemRepository;
    private final GenreRepository genreRepository;

    public DataInitializer(MediaItemRepository mediaItemRepository, GenreRepository genreRepository) {
        this.mediaItemRepository = mediaItemRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {
        if (mediaItemRepository.count() > 0) {
            System.out.println(">>> Database already seeded! Current count: " + mediaItemRepository.count());
            return;
        }

        Genre sciFi = genreRepository.findByNameIgnoreCase("Sci-Fi")
                .orElseGet(() -> genreRepository.save(new Genre("Sci-Fi")));
        Genre thriller = genreRepository.findByNameIgnoreCase("Thriller")
                .orElseGet(() -> genreRepository.save(new Genre("Thriller")));

        MediaItem item = new MediaItem();
        item.setExternalId("demo:1");
        item.setExternalSource("SEED");
        item.setMediaType(MediaType.ANIME);
        item.setTitle("Steins;Gate");
        item.setOriginalTitle("シュタインズ・ゲート");
        item.setOverview("A self-proclaimed mad scientist discovers the means of sending text messages to the past.");
        item.setReleaseDate(LocalDate.of(2011, 4, 6));
        item.setEpisodes(24);
        item.setVoteAverage(new BigDecimal("9.1"));
        item.setPopularity(new BigDecimal("95.420"));
        item.setStatus("FINISHED");
        item.setGenres(Set.of(sciFi, thriller));

        mediaItemRepository.save(item);

        System.out.println("=================================================");
        System.out.println(">>> SUCCESS: Seed record saved! ID = " + item.getId());
        System.out.println("=================================================");
    }
}
