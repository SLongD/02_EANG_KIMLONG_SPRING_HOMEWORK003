package org.kps.exceptionhadling.repository;


import org.apache.ibatis.annotations.*;
import org.kps.exceptionhadling.model.Venues;
import org.kps.exceptionhadling.model.dto.RequestVenue;

import java.util.List;

@Mapper
public interface VenuesRepository {
    @Select("""
        select * from venues
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id=("venuesMap"), value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "venueLocation", column = "location")
    })
    List<Venues> findAllVenuesWithPagination(Integer page, Integer size);

    @Select("""
        select * from venues where venue_id = #{id}
    """)
    @ResultMap("venuesMap")
    Venues findAllVenueById(Integer id);

    @Select("""
        insert into venues ("venue_name", "location") 
        values (#{venue.venueName}, #{venue.venueLocation})
        returning *;
    """)
    @ResultMap("venuesMap")
    Venues insertVenue(@Param("venue") RequestVenue requestVenue);

    @Select("""
        update venues
        set venue_name = #{venue.venueName}, location = #{venue.venueLocation}
        where venue_id = #{id}
        returning *;
    """)
    @ResultMap("venuesMap")
    Venues updateVenueById(Integer id,@Param("venue") RequestVenue requestVenue);

    @Delete("""
        delete from venues where venue_id = #{id}
    """)
    Integer deleteVenueById(Integer id);
}
