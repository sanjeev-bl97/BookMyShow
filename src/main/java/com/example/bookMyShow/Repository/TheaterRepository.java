package com.example.bookMyShow.Repository;
import com.example.bookMyShow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TheaterRepository extends JpaRepository<Theater,Integer> {

    Theater findByLocation(String location);

    @Query(value ="select count(*) from theater where name = :theaterName" ,nativeQuery = true)
    Integer getCountByName(String theaterName);


}
