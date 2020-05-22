package com.journal.blogstore.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journal.blogstore.Documents.BlogDocument;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.MinimumScoreCollector;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class ElasticUtils {

    @Autowired
    RestHighLevelClient client;

    @Autowired
    ObjectMapper objectMapper;

    public void indexBlogDoc(BlogDocument document) throws IOException {
        UUID uuid = UUID.randomUUID();
        document.setId(uuid.toString());

        Map<String, Object> documentMap = objectMapper.convertValue(document,Map.class);

        IndexRequest request = new IndexRequest("blogs");
        request.id(document.getId());
        request.source(documentMap);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

    }

    public String searchBlogsByAuthor(String authorName) throws IOException {

        SearchRequest searchRequest = new SearchRequest("blogs");
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("authorName",authorName);
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        sourceBuilder.query(matchQueryBuilder);

        searchRequest.source(sourceBuilder);

        return client.search(searchRequest,RequestOptions.DEFAULT).toString();
    }

    public String searchBlogsByTitleAndContent(String queryString) throws IOException {

        SearchRequest searchRequest = new SearchRequest("blogs");
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchTitleQueryBuilder = new MatchQueryBuilder("title",queryString);
        matchTitleQueryBuilder.autoGenerateSynonymsPhraseQuery(true);
        matchTitleQueryBuilder.fuzziness(Fuzziness.AUTO);


        MatchQueryBuilder matchContentQueryBuilder = new MatchQueryBuilder("content",queryString);
        matchContentQueryBuilder.autoGenerateSynonymsPhraseQuery(true);
        matchContentQueryBuilder.fuzziness(Fuzziness.AUTO);

        sourceBuilder.query(QueryBuilders.boolQuery().should(matchContentQueryBuilder).should(matchTitleQueryBuilder));

        searchRequest.source(sourceBuilder);

        return client.search(searchRequest,RequestOptions.DEFAULT).toString();
    }

}
