
CREATE TABLE Users(UserId INT NOT NULL AUTO_INCREMENT, UserName VARCHAR(100) NOT NULL , PRIMARY KEY ( UserId ));

CREATE TABLE Courses(CourseId INT NOT NULL AUTO_INCREMENT, CourseName VARCHAR(100) NOT NULL , PRIMARY KEY ( CourseId ));

CREATE TABLE Libraries(LibraryId INT NOT NULL AUTO_INCREMENT, LibraryName VARCHAR(100) NOT NULL , PRIMARY KEY ( LibraryId ));

CREATE TABLE Rooms(RoomId INT NOT NULL AUTO_INCREMENT, RoomName VARCHAR(100) NOT NULL , LibraryId INT NOT NULL, PRIMARY KEY ( RoomId ),
CONSTRAINT fk_LibRoom FOREIGN KEY (LibraryId) REFERENCES Libraries(LibraryId));

CREATE TABLE Spaces(SpaceId INT NOT NULL AUTO_INCREMENT, SpaceName VARCHAR(100) NOT NULL , LibraryId INT NOT NULL, PRIMARY KEY ( SpaceId ),
CONSTRAINT fk_LibSpace FOREIGN KEY (LibraryId) REFERENCES Libraries(LibraryId));

CREATE TABLE UserCourse(UserId INT , CourseId INT , PRIMARY KEY ( UserId, CourseId ),
CONSTRAINT fk_User FOREIGN KEY (UserId) REFERENCES Users(UserId),
CONSTRAINT fk_Course FOREIGN KEY (CourseId) REFERENCES Courses(CourseId));

CREATE TABLE Friendships(User1 INT , User2 INT , PRIMARY KEY ( User1, User2 ),
CONSTRAINT fk_User1 FOREIGN KEY (User1) REFERENCES Users(UserId),
CONSTRAINT fk_User2 FOREIGN KEY (user2) REFERENCES Users(UserId));

'CREATE TABLE StudyGroups(StudyGroupId INT NOT NULL AUTO_INCREMENT, StudyGroupName VARCHAR(100) NOT NULL , 
LibraryId INT NOT NULL, PRIMARY KEY ( RoomId ),
CONSTRAINT fk_LibRoom FOREIGN KEY (LibraryId) REFERENCES Libraries(LibraryId));'