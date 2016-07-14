package com.fom.msesoft.fomapplication.repository;


import com.fom.msesoft.fomapplication.model.Person;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;



/**
 * Created by oguz on 30.06.2016.
 */
@Rest(rootUrl = "http://192.168.2.120:8081/person",converters = { MappingJackson2HttpMessageConverter.class })
public interface PersonRepository {

    @Get("/findByFirstName?name=A1")
    Person findByFirstName();

    @Get("/singIn?email={email}&password={password}")
    Person signIn (@Path String email, @Path String password);

    @Get("/findByFirstDegreeFriend?uniqueId=55a2678e-5c30-4928-90a0-94151307bc11")
    Person[] findByFirstDegreeFriend();

    @Get("/workNotFriend?name=A1")
    Person[] workNotFriend();

    @Post("/singUp")
    Person insert(@Body Person person);
}