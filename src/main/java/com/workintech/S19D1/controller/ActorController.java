package com.workintech.S19D1.controller;

import com.workintech.S19D1.dto.ActorRequest;
import com.workintech.S19D1.dto.ActorRespose;
import com.workintech.S19D1.entity.Actor;
import com.workintech.S19D1.entity.Movie;
import com.workintech.S19D1.repository.ActorRepository;
import com.workintech.S19D1.service.ActorService;
import com.workintech.S19D1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/actors")
public class ActorController {
  private ActorService actorService;
@Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/")
    public ActorRespose save(@Validated @RequestBody ActorRequest actorRequest){
        List<Movie> movies=actorRequest.getMovies();
        Actor actor=actorRequest.getActor();

        for (Movie movie:movies){
            actor.addMovie(movie);
        }
    actorService.save(actor);
       return Converter.actorResponseConvert(actor);

    }
    @GetMapping("/")
    public List<ActorRespose> findAll(){
        List<Actor> actors=actorService.findAll();
        return Converter.actorResponseConvert1(actors);
   }

   @GetMapping("/{id}")
    public ActorRespose findById(@PathVariable long id){
      Actor actor=actorService.findById(id);
     return Converter.actorResponseConvert(actor);

   }
   @PutMapping("/{id}")
   public ActorRespose update(@RequestBody Actor actor,@PathVariable long id){
    Actor foundActor= actorService.findById(id);
    actor.setMovies(foundActor.getMovies());
    actor.setId(foundActor.getId());
    actorService.save(actor);
    return Converter.actorResponseConvert(actor);

   }

   @DeleteMapping("/{id}")
    public ActorRespose delete(@PathVariable long id){
    Actor actor=actorService.findById(id);
    actorService.delete(id);
    return Converter.actorResponseConvert(actor);
   }
}


