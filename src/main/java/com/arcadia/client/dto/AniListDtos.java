package com.arcadia.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class AniListDtos {

    public record GraphQLRequest(String query, Variables variables) {}

    public record Variables(int page, int perPage) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AniListResponse(AniListData data) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AniListData(PageData Page) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PageData(PageInfo pageInfo, List<AniListMedia> media) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PageInfo(int total, int currentPage, int lastPage, boolean hasNextPage, int perPage) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AniListMedia(
        int id,
        MediaTitle title,
        String description,
        FuzzyDate startDate,
        Integer episodes,
        Integer averageScore,
        Integer popularity,
        String status,
        List<String> genres
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MediaTitle(
        String romaji,
        String english,
        String nativeTitle
    ) {
        public MediaTitle {
            // Spring Jackson uses field reflection, but record accessors remain clean
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record FuzzyDate(
        Integer year,
        Integer month,
        Integer day
    ) {}
}
