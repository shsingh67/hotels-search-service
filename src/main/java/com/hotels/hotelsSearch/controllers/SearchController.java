package com.hotels.hotelsSearch.controllers;

import com.hotels.hotelsSearch.models.Hotel;
import com.hotels.hotelsSearch.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/hotels")
public class SearchController {

    private SearchService yelpService;

    public SearchController(SearchService yelpService) {
        this.yelpService = yelpService;
    }

    @RequestMapping(method=RequestMethod.GET)
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity retrieveHotels(
            @RequestParam(value="location", required = false) String location) {

        String response = yelpService.retrieveHotel(location);

        return new ResponseEntity(response, HttpStatus.OK);

    }

}
