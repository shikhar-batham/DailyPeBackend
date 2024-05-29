First fo all create a Databse in PostgreSQL by the name dailyPeBe and run the project.

Please find below curls for specific perpose 

CREATE MANAGER
-------------------------
curl --location 'http://localhost:8080/api/v1/manager/createManager' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Jay Prakash"
}'

It resuls a unique manager id that you can provide in create user json to asign a manager to a user
----------------------------------------------------------

CREATE USER
--------------------

curl --location 'http://localhost:8080/api/v1/user/createUser' \
--header 'Content-Type: application/json' \
--data '{
    "fullName": "Manisha Batham",
    "mobileNumber": "9198192914",
    "panNumber": "bbBCP1234B",
    "managerId": "fdf6bd30-56ac-4057-af5f-aeb4f93d15a8"
}'

--------------------------------------------------------------------------------------

DELETE USER
---------------------------

curl --location 'http://localhost:8080/api/v1/user/deleteUser' \
--header 'Content-Type: application/json' \
--data '{
    "uuid": "n",
    "mobileNumber": "9555622565"
}'

To delete a user you can provider either uuid or user of mobile number or both.
--------------------------------------------------------------------------------------------

UPDATE USER
----------------------
curl --location 'http://localhost:8080/api/v1/user/updateUser/1' \
--header 'Content-Type: application/json' \
--data '{
    "fullName": "Prakhar Batham",
    "mobileNumber": "9198192913",
    "panNumber": "AABCP1234B",
    "managerId": "fdf6bd30-56ac-4057-af5f-aeb4f93d15a8"
}'
