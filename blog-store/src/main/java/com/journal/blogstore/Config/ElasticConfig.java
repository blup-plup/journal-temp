package com.journal.blogstore.Config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

    @Value("$elasticsearch.host")
    private String host;

    @Value("$elasticsearch.port")
    private String port;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }



    @Bean
    public RestHighLevelClient client(){
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost",9200,"http"));
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);

        return client;
    }
}
