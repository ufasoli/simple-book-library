package com.ufasoli.vaadin.library.spring.config;

import com.mongodb.Mongo;
import com.ufasoli.vaadin.library.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 10:34
 */
@Configuration
@ComponentScan(basePackages = {"com.ufasoli.vaadin.library","com.ufasoli.vaadin.library.web" })
@EnableMongoRepositories(basePackages = "com.ufasoli.vaadin.library.spring.dao")
public class Application {

    @Bean
    public Mongo mongo() throws UnknownHostException {

        return new Mongo("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {

        MongoTemplate mongoTemplate = new    MongoTemplate(mongo(), "book-library");


        if(!mongoTemplate.collectionExists(Book.class)){
                   bootstrapData(mongoTemplate);
        }

      return mongoTemplate;
    }


    private void bootstrapData(MongoTemplate mongoTemplate){

        System.out.println("********************************************");
        System.out.println("Bootstrapping sample data for mongodb app");
        System.out.println("********************************************");
        mongoTemplate.save(new Book(String.valueOf(Math.abs(new Random().nextInt())), "Book of Vaadin", "Programming", "ufasoli", new Date() ));
        mongoTemplate.save(new Book(String.valueOf(Math.abs(new Random().nextInt())), "MongoDB in Action", "NoSQL" ));
        mongoTemplate.save(new Book(String.valueOf(Math.abs(new Random().nextInt())), "Scala in Action", "Functional programming" ));
        mongoTemplate.save(new Book(String.valueOf(Math.abs(new Random().nextInt())), "Mastering Web Application Development with AngularJS", "Programming" ));
        mongoTemplate.save(new Book(String.valueOf(Math.abs(new Random().nextInt())), "The Well-Grounded Java Developer", "Programming" ));
        mongoTemplate.save(new Book(String.valueOf(Math.abs(new Random().nextInt())), "Neo4j in Action", "NoSQL" ));


    }
}
