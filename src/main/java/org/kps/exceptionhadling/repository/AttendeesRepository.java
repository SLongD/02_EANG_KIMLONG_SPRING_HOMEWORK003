package org.kps.exceptionhadling.repository;

import org.apache.ibatis.annotations.*;
import org.kps.exceptionhadling.model.Attendees;
import org.kps.exceptionhadling.model.dto.RequestAttendee;

import java.util.List;

@Mapper
public interface AttendeesRepository {
    @Select("""
        select * from attendees
        offset #{size} * (#{page} -1)
        limit #{size}
    """)
    @Results(id=("attendeesMap"), value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "attendeeEmail", column = "email")
    })
    List<Attendees> findAllAttendeesWithPage(Integer page, Integer size);

    @Select("""
        select * from attendees
        where attendee_id = #{id}
    """)
    @ResultMap("attendeesMap")
    Attendees findAttendeeById(Integer id);

    @Select("""
        select at.attendee_id, at.attendee_name, at.email 
        from attendees at inner join event_attendee ea
        on at.attendee_id = ea.attendee_id
        where ea.event_id = #{eventId}
    """)
    @ResultMap("attendeesMap")
    List<Attendees> findAllAttendeesByEventId(Integer eventId);

    @Select("""
        insert into attendees (attendee_name, email)
        values (#{attendees.attendeeName}, #{attendees.attendeeEmail})
        returning *;
    """)
    @ResultMap("attendeesMap")
    Attendees insertAttendee(@Param("attendees") RequestAttendee requestAttendee);

    @Select("""
        update attendees
        set attendee_name=#{attendee.attendeeName}, email=#{attendee.attendeeEmail}
        where attendee_id=#{id}
        returning *;
    """)
    @ResultMap("attendeesMap")
    Attendees updateAttendeeById(Integer id,@Param("attendee") RequestAttendee requestAttendee);

    @Delete("""
        delete from attendees 
        where attendee_id = #{id}
    """)
    int deleteAttendeeById(Integer id);
}
