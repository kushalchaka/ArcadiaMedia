package com.arcadia.client;

import com.arcadia.client.dto.AniListDtos.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@Component
public class AniListClient {

    private static final String ANILIST_URL = "https://graphql.anilist.co";

    private static final String POPULAR_ANIME_QUERY = """
        query ($page: Int, $perPage: Int) {
          Page(page: $page, perPage: $perPage) {
            pageInfo {
              total
              currentPage
              lastPage
              hasNextPage
              perPage
            }
            media(type: ANIME, sort: POPULARITY_DESC) {
              id
              title {
                romaji
                english
                nativeTitle: native
              }
              description(asHtml: false)
              startDate {
                year
                month
                day
              }
              episodes
              averageScore
              popularity
              status
              genres
            }
          }
        }
        """;

    private final RestClient restClient;

    public AniListClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(ANILIST_URL)
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<AniListMedia> fetchPopularAnime(int page, int perPage) {
        GraphQLRequest requestPayload = new GraphQLRequest(
                POPULAR_ANIME_QUERY,
                new Variables(page, perPage)
        );

        AniListResponse response = restClient.post()
                .body(requestPayload)
                .retrieve()
                .body(AniListResponse.class);

        if (response != null && response.data() != null && response.data().Page() != null) {
            return response.data().Page().media();
        }

        return Collections.emptyList();
    }
}
