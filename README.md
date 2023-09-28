# YouthMeeting project
<details>
<summary>Current Task TODOs</summary>

- CRUD of meeting
- Add attendance
- meeting filtering
- getting attendance 
- tambola

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
- [x] add edit meeting api
- [ ] add to the util list filtration methods that filter list of youths or list of meetings depending on the filterDTO
- [ ] make the meeting dto check the date if the date is friday or any user specified day it's of otherwise throw exception
- [ ] add the exceptions and error
- [ ] add Unit test cases of the meeting repo, service, controller
- [ ] modify the specifications for more optimized OOP
- [ ] check if no login the back end panic
- [ ] add the tambola
- [ ] in tambola you need to specify some criteria of the selected winners as gender.
  
  
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
</details>

---

