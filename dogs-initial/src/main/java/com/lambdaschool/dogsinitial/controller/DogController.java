package com.lambdaschool.dogsinitial.controller;

import com.lambdaschool.dogsinitial.DogsInitialApplication;
import com.lambdaschool.dogsinitial.DogsInitialApplication;
import com.lambdaschool.dogsinitial.exception.ResourceNotFoundException;
import com.lambdaschool.dogsinitial.model.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/dogs")
public class DogController
{
    private static final Logger logger = LoggerFactory.getLogger(DogController.class);



    // localhost:8080/dogs/dogs
    @GetMapping(value = "/dogs",produces = {"application/json"})
    public ResponseEntity<?> getAllDogs(HttpServletRequest request)
    {
        logger.info(request.getRequestURI() + " accessed");

        return new ResponseEntity<>(DogsInitialApplication.ourDogList.dogList, HttpStatus.OK);
    }



    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDogDetail(HttpServletRequest request,@PathVariable long id)throws ResourceNotFoundException
    {

        logger.trace(request.getRequestURI() + " accessed");

        Dog rtnDog;
        if (DogsInitialApplication.ourDogList.findDog(d -> (d.getId() == id)) == null)
        {
            throw new ResourceNotFoundException("Dog with id " + id + " not found!");
        } else
        {
            rtnDog = DogsInitialApplication.ourDogList.findDog(d -> (d.getId() == id));
        }
        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }




    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}",produces = {"application/json"})
    public ResponseEntity<?> getDogBreeds (HttpServletRequest request,@PathVariable String breed)throws ResourceNotFoundException
    {

        logger.trace(request.getRequestURI() + " accessed");

        ArrayList<Dog> rtnDogs;
        if (DogsInitialApplication.ourDogList.findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()))==null)
        {
            throw new ResourceNotFoundException("Dog breed " + breed+" not found!");
        } else
        {
            rtnDogs=DogsInitialApplication.ourDogList.findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        }
//        ArrayList<Dog> rtnDogs = DogsInitialApplication.ourDogList.findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }




    // localhost:8080/dogs/dogtable
    @GetMapping(value = "/dogtable")
    public ModelAndView displayDogTable(HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("dogs");

        DogsInitialApplication.ourDogList.dogList.sort((d1, d2) -> d1.getBreed().compareToIgnoreCase(d2.getBreed()));
        mav.addObject("dogList", DogsInitialApplication.ourDogList.dogList);

        return mav;
    }




    // localhost:8080/dogs/dogtable
    @GetMapping(value = "/aptdogtable")
    public ModelAndView displayAptDogTable(HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("dogs");

        ArrayList<Dog> rtnDogs = DogsInitialApplication.ourDogList.
                findDogs(d -> d.isApartmentSuitable());

        rtnDogs.sort((d1, d2) -> d1.getBreed().compareToIgnoreCase(d2.getBreed()));
        mav.addObject("dogList", rtnDogs);

        return mav;
    }
}
