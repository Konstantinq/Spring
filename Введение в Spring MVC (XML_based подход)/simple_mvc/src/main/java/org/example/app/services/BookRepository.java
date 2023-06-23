package org.example.app.services;


import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository<T> implements ProjectRepository<Book>, ApplicationContextAware {

    private final List<Book> repo = new ArrayList<>();

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private ApplicationContext context;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs , int rowNum) ->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }

    public void store(Book book) {
       // book.setId(context.getBean(IdProvider.class).providerId(book));
        //repo.add(book);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", book.getAuthor());
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("size", book.getSize());

        jdbcTemplate.update("INSERT INTO books(author, title, size) VALUES(:author, :title, :size)", parameterSource);
    }

    public boolean removeToItemById(Integer bookIdToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookIdToRemove);
        jdbcTemplate.update("DELETE FROM books WHERE id =:id", parameterSource);
        return true;

//        for(Book book: retreiveAll()){
//            if (book.getId().equals(bookIdToRemove)){
//                return repo.remove(book);
//            }
//        }
//        return false;
    }

    @Override
    public void removeToItemByRegex(String regexWord) {
        for(Book book: retreiveAll()){
            if (book.getAuthor().equals(regexWord) || book.getTitle().equals(regexWord)){
                 repo.remove(book);
            } else if (regexWord.matches("\\d+")){
                if (book.getSize().equals( Integer.parseInt(regexWord))){
                    repo.remove(book);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void defaultInit(){
        System.out.println("default INIT in book service");
    }

    public void defaultDestroy(){
        System.out.println("default DESTROY in book service");
    }
}
