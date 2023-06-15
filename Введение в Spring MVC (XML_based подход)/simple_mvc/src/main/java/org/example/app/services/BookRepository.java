package org.example.app.services;


import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository<T> implements ProjectRepository<Book>, ApplicationContextAware {

    private final List<Book> repo = new ArrayList<>();

    private ApplicationContext context;
    public List<Book> retreiveAll() {
        return new ArrayList<Book>(repo);
    }

    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
       // book.setId(String.valueOf(book.hashCode()));

        repo.add(book);
    }

    public boolean removeToItemById(String bookIdToRemove) {
        for(Book book: retreiveAll()){
            if (book.getId().equals(bookIdToRemove)){
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public void removeToItemByRegex(String regexWord) {
        for(Book book: retreiveAll()){
            if (book.getAuthor().equals(regexWord) || book.getTitle().equals(regexWord)){
                 repo.remove(book);
            } else if (regexWord.matches("\\d+")){
                if (book.getSize().equals(Integer.parseInt(regexWord))){
                    repo.remove(book);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit(){

    }

    private void defaultDestroy(){

    }
}
