# YouthMeeting project
<details>
<summary>TODO</summary>

- use sonarqube
- address the PR

</details>

---
<details>
<summary>learnt</summary>

- filtering with specification form the DB
- Basic Auth 
- mappers using MapStruct
- DataBase migration with flyway
- clean code with sonarLint
</details>

---
# Today Tasks
- finish the first PR
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
  - [ ] add gender to youth, to mapper, to filterDTO, to specification
  - [ ] exceptions
  - [ ] rename the end points
  - [ ] sonarlint
  - 
  
----
# done tasks
- finish the first PR
  - [x] change the IDs to Long
  - [x] change the uni level to String
  - [x] use flyway
  - [x] add DB migration (ddl sql statements)
  - [x] use sonarLint
---
# meeting comments

- change the hard coded details
- @MappedSuperclass for basic entity
