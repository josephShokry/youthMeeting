# YouthMeeting project
<details>
<summary>Current Task TODOs</summary>

- CRUD of meeting
- Add attendance
- meeting filtering
- getting attendance 
- raffle 

</details>

---
<details>
<summary>learnt</summary>

- filtering with specification form the DB
- security using spring security
- Basic Auth 
- mappers using MapStruct
- DataBase migration with flyway
- clean code with sonarLint
- resource Bundle
- documenting apis with swagger
- parametrized tests
- mvc integration testing
- mock testing
- Lombok : `@Superbuilder` that solves the inheritance builder
</details>



---
# Today Tasks
- [ ] add DB Migrations
- [ ] add the exceptions and error (search if there is any missing)
- [ ] add Unit test cases of the meeting repo, service, controller
- [ ] add church to the servant (add church to the dto and when it is null then use the default "el malak mekhail" )
- [ ] make the meeting dto check the date if the date is friday or any user specified day it's of otherwise throw exception
- 
- [x] add constrain on the attendance table that youthId concat with date must be unique
- [x] make sure no repeated name or youth or anything in the lists like the attendance
- [x] modify the specifications for more optimized OOP
- [x] capture the time while adding the attendance and filter with it
- [x] if the number of prizes bigger than list size it panics
- [x] meeting instructor should be basic youth
- [x] attendance filter with 2 request body
  
----
# done tasks
<details>
<summary>First PR</summary>

- finish the first PR
  - [x] change the IDs to Long
  - [x] change the uni level to String
  - [x] use flyway
  - [x] add DB migration (ddl sql statements)
  - [x] use sonarLint
  - [x] remove the test files and use the updated one
  - [x] solve bug with filtering with name part
  - [x] remove unnecessary lists of the entities
  - [x] modify the packages
  - [x] unify the name attribute across all the entities
  - [x] optimize mapping the lists by using mapstruct interface instead of my implementation
  - [x] make pagination super class of filterDTO class
  - [x] remove unnecessary DTOs like areaDTO and familyLightDTO
  - [x] make all DTOs extends from the LightDTO
  - [x] change the name of intermediateYouthDto
  - [x] unify  the return type just return ID or return boolean
  - [x] remove the `@context` of the mappers
  - [x] make sure to unify the logic across entities
  - [x] use private in all DTOs
  - [x] change the inheritance of the person
  - [x] add gender to youth, youthDto, mapper, filterDTO, specification
  - [x] add an exception handler for catching the exception to enum
  - [x] exceptions
  - [x] add tests of the gender and its exceptions and filter
  - [x] messages
  - [x] simplify the names of mappers methods
  - [x] sonarlint
  - [x] rename the end points
  - [x] pagination constrains
  - [x] merge with swagger branch
  - [x] finish the pull request and squash and merge to the develop branch
</details>

<details>
<summary>Second PR</summary>
</details>

<details>
<summary>Third PR</summary>

- [x] add all needed end points of meeting controller
- [x] add all needed entities, dtos, mappers, services, ...
- [x] why the instructor in the meeting must be unique
- [x] why the encoder doesn't work
- [x] add security to the meeting
- [x] add edit meeting api
- [x] add to the util list filtration methods that filter list of youths or list of meetings depending on the filterDTO
- [x] add the tambola
- [x] in tambola you need to specify some criteria of the selected winners as gender.
</details>

---
<details>
<summary>commented code</summary>

  ```roomsql
  -- this code is the sql query of get attendance method in attendance service
      SELECT a.youth_id
      FROM attendances a
      JOIN (
          SELECT m.id
          FROM meeting m
          WHERE MONTH(m.date) = 9
      ) filtered_meetings ON a.meeting_id = filtered_meetings.id
      JOIN persons y ON a.youth_id = y.id -- Add JOIN with the Youth table
      WHERE y.gender = 'FEMALE' -- Add conditions on Youth entities here
      GROUP BY a.youth_id
      HAVING COUNT(DISTINCT a.meeting_id) = (SELECT COUNT(*) FROM meeting WHERE MONTH(date) = 9);
  
  ```
***
```java
//this is used to filter the meetings usein meetingDTO
//    public Boolean matches(MeetingFiltersDTO meetingFiltersDTO){
//        return (meetingFiltersDTO.getDay() == null || meetingFiltersDTO.getDay().equals(this.getDate().getDayOfMonth()))
//                && (meetingFiltersDTO.getMonth() == null || meetingFiltersDTO.getMonth().equals(this.getDate().getMonthValue()))
//                && (meetingFiltersDTO.getYear() == null || meetingFiltersDTO.getYear().equals(this.getDate().getYear()))
//                && (meetingFiltersDTO.getInstructorId() == null || meetingFiltersDTO.getInstructorId().equals(this.getInstructor().getId()))
//                && (meetingFiltersDTO.getTopic() == null || this.getTopic().contains(meetingFiltersDTO.getTopic()));
//    }

```
</details>
