package com.hotels.hotelsSearch.service;

import com.hotels.hotelsSearch.dao.HotelDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("yelpService")
public class YelpService implements SearchService {

    @Value("${url.api.yelp}")
    private String urlApiYelp;

    private HotelDAO hotelDAO;
    private final RestTemplate restTemplate;
    private HttpEntity<String> entity;


    public YelpService(HotelDAO hotelDAO, RestTemplateBuilder restTemplateBuilder, HttpEntity<String> entity) {
        this.hotelDAO = hotelDAO;
        this.restTemplate = restTemplateBuilder.build();
        this.entity = entity;
    }

    public String retrieveHotel(String location) {

        String hotels = hotelDAO.retrieveHotels(location);

        if(hotels == null) {
            String reqURL = urlApiYelp + location;
            ResponseEntity<String> response = restTemplate.exchange(reqURL, HttpMethod.GET, entity, String.class);
            hotelDAO.add(location, response.getBody());
            return response.getBody();
        }

        return hotels;
    }
}
