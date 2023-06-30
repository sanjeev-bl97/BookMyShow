package com.example.bookMyShow.Repository;

import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Integer> {

    @Query(value = "select movie_id from shows group by movie_id order by count(*) desc limit 1 ",nativeQuery = true)
    public Integer getMostShowedMovie();

    @Query(value = "select show_time from shows where movie_id = :movie and theater_id = :theater",nativeQuery = true)
    List<String> getShowTime(int movie,int theater);

    List<Show> findByShowTime(String showTime);
}
