package com.example.TBA.repository;

import com.example.TBA.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
//
//
//}



@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {



    @Query(value = "select ip_address from customer_info", nativeQuery = true)
    public List<String> findAllIps();

    @Query(value = "SELECT  browser,\n" +
            "             COUNT(browser) AS browser_occurrence \n" +
            "    FROM     customer_info\n" +
            "    GROUP BY browser\n" +
            "    ORDER BY browser_occurrence DESC\n" +
            "    LIMIT    1;", nativeQuery = true)
    public String mostPopularBrowser();



    @Query(value ="select created_date from customer_info", nativeQuery = true)
    public List<Object> dates();

    @Query(value ="SELECT COUNT(DISTINCT ip_address) FROM customer_info WHERE created_date >= date_trunc('day', now()) + interval '1h'", nativeQuery = true)
    public List<Object> lastHour();

    @Query(value ="select COUNT(created_date) from customer_info", nativeQuery = true)
    public String numberOfRequests();



}