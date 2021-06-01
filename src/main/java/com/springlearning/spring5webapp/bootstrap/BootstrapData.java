package com.springlearning.spring5webapp.bootstrap;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springlearning.spring5webapp.domain.Author;
import com.springlearning.spring5webapp.domain.Book;
import com.springlearning.spring5webapp.domain.Publisher;
import com.springlearning.spring5webapp.repositories.AuthorRepository;
import com.springlearning.spring5webapp.repositories.BookRepository;
import com.springlearning.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap Data");

        Publisher publisherA = new Publisher();
        publisherA.setName("A Publications");
        publisherA.setAddressLine1("Address Line 1");
        publisherA.setState("Raj");
        publisherA.setCity("Jaipur");
        publisherA.setZipCode("302001");
        publisherRepository.save(publisherA);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        publisherA.getBooks().add(ddd);
        ddd.setPublisher(publisherA);
        authorRepository.save(eric);
        bookRepository.save(ddd);


        Author rod = new Author("Rod", "Johnson");
        Book myb = new Book("My Book", "123124");
        rod.getBooks().add(myb);
        myb.getAuthors().add(rod);
        publisherA.getBooks().add(myb);
        myb.setPublisher(publisherA);

        authorRepository.save(rod);
        bookRepository.save(myb);
        publisherRepository.save(publisherA);


        System.out.println("Books Count " + publisherRepository.count());
        System.out.println("Books published by PublisherA " + publisherA.getBooks().size());
    }
}
