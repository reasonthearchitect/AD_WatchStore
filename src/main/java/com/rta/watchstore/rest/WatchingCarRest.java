package com.rta.watchstore.rest;

import com.rta.watchstore.repo.IWatchingCarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchingcars")
public class WatchingCarRest {

    @Autowired private IWatchingCarRepo watchingCarRepo;

    @RequestMapping(value = "/for/{name}/carvin/{carid}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> watch(@PathVariable String name, @PathVariable String carid) {
        this.watchingCarRepo.save(name, carid);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/remove/{name}/carvin/{carvin}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> unwatch(@PathVariable String name, @PathVariable String carvin) {
        this.watchingCarRepo.delete(name, carvin);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/getwatchlist/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getCar(@PathVariable String name) {
        return new ResponseEntity<>(this.watchingCarRepo.findAllForPerson(name), HttpStatus.OK);
    }
}
