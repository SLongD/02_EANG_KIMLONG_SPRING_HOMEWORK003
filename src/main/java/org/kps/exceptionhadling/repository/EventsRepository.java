package org.kps.exceptionhadling.repository;

import org.apache.ibatis.annotations.*;
import org.kps.exceptionhadling.model.Events;
import org.kps.exceptionhadling.model.dto.RequestEvent;

import java.util.List;

@Mapper
public interface EventsRepository {
    @Select("""
        select * from events
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id="eventsMap", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date" ),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.kps.exceptionhadling.repository.VenuesRepository.findAllVenueById")
            ),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "org.kps.exceptionhadling.repository.AttendeesRepository.findAllAttendeesByEventId")
            )
    })
    List<Events> findAllEventsWithPagination(Integer page, Integer size);

    @Select("""
        select * from events
        where event_id = #{id}
    """)
    @ResultMap("eventsMap")
    Events findEventById(Integer id);


    @Select("""
        insert into events (event_name, event_date, venue_id)
        values (#{event.eventName},#{event.eventDate},#{event.venueId})
        returning event_id;
    """)
    Integer insertEvent(@Param("event") RequestEvent requestEvent);

    @Select("""
        insert into event_attendee (event_id, attendee_id)
        values (#{eventId}, #{attendeeId})
        on conflict (event_id, attendee_id) do nothing;
    """)
    void insertEventAttendee(Integer eventId, Integer attendeeId);


    @Select("""
        update events set event_name = #{event.eventName}, event_date = #{event.eventDate}, venue_id = #{event.venueId}
        where event_id = #{id}
        returning event_id;
    """)
    Integer updateEventById(Integer id,@Param("event") RequestEvent requestEvent);


    @Select("""
        insert into event_attendee (event_id, attendee_id)
        values (#{updateEventId}, #{attendeeId})
        on conflict (event_id, attendee_id) do nothing;
    """)
    void insertNewEventAttendee(Integer updateEventId, Integer attendeeId);

    @Select("""
        select attendee_id from event_attendee
        where event_id = #{eventId}
    """)
    List<Integer> findAttendeeIdsByEventId(Integer updateEventId);

    @Delete("""
        <script>
            DELETE FROM event_attendee
            WHERE event_id = #{eventId}
            <if test='attendeeIds != null and !attendeeIds.isEmpty()'>
                AND attendee_id IN\s
                <foreach item='item' collection='attendeeIds' open='(' separator=',' close=')'>
                    #{item}
                </foreach>
            </if>
        </script>
    """)
    void removeEventAttendees(@Param("eventId") Integer eventId, @Param("attendeeIds") List<Integer> attendeeIds);

    @Delete("""
        delete from events where event_id = #{id}
    """)
    int deleteEventById(Integer id);
}
