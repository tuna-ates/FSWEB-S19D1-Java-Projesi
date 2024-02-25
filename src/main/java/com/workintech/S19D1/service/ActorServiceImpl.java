package com.workintech.S19D1.service;

import com.workintech.S19D1.entity.Actor;
import com.workintech.S19D1.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{
    private ActorRepository actorRepository;
    private MovieService movieService;
@Autowired
    public ActorServiceImpl(ActorRepository actorRepository, MovieService movieService) {
        this.actorRepository = actorRepository;
        this.movieService = movieService;
    }

    @Override
    public List<Actor> findAll() {
      return actorRepository.findAll();

    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);

    }



    @Override
    public Actor findById(long id) {

        Optional<Actor> actorOptional = actorRepository.findById(id);
      if (actorOptional.isPresent()){
          return actorOptional.get();
      }
      //TODO EXCEPTİON EKLE
      throw new RuntimeException("bu id'de actor yok");
    }

    @Override
    public void delete(long id) {
       Actor actor =findById(id);
        actorRepository.delete(actor);
    }

}
