
CREATE TABLE Users(UserId INT NOT NULL AUTO_INCREMENT, UserName NVARCHAR(100) NOT NULL , PRIMARY KEY ( UserId ));

CREATE TABLE Courses(CourseId INT NOT NULL IDENTITY(1,1), CourseName NVARCHAR(100) NOT NULL , PRIMARY KEY ( CourseId ));

CREATE TABLE Libraries(LibraryId INT NOT NULL IDENTITY(1,1), LibraryName NVARCHAR(100) NOT NULL , PRIMARY KEY ( LibraryId ));

CREATE TABLE Rooms(RoomId INT NOT NULL IDENTITY(1,1), RoomName NVARCHAR(100) NOT NULL , LibraryId INT NOT NULL, PRIMARY KEY ( RoomId ),
CONSTRAINT fk_LibRoom FOREIGN KEY (LibraryId) REFERENCES Libraries(LibraryId));

CREATE TABLE Spaces(SpaceId INT NOT NULL IDENTITY(1,1), SpaceName NVARCHAR(100) NOT NULL , LibraryId INT NOT NULL, PRIMARY KEY ( SpaceId ),
CONSTRAINT fk_LibSpace FOREIGN KEY (LibraryId) REFERENCES Libraries(LibraryId));

CREATE TABLE UserCourse(UserId INT , CourseId INT , PRIMARY KEY ( UserId, CourseId ),
CONSTRAINT fk_User FOREIGN KEY (UserId) REFERENCES Users(UserId),
CONSTRAINT fk_Course FOREIGN KEY (CourseId) REFERENCES Courses(CourseId));

CREATE TABLE Friendships(User1 INT , User2 INT , PRIMARY KEY ( User1, User2 ),
CONSTRAINT fk_User1 FOREIGN KEY (User1) REFERENCES Users(UserId),
CONSTRAINT fk_User2 FOREIGN KEY (user2) REFERENCES Users(UserId));

CREATE TABLE StudyGroups(StudyGroupId INT NOT NULL AUTO_INCREMENT, StudyGroupName NVARCHAR(100) NOT NULL , 
LibraryId INT NOT NULL, PRIMARY KEY ( RoomId ),
CONSTRAINT fk_LibRoom FOREIGN KEY (LibraryId) REFERENCES Libraries(LibraryId));

INSERT INTO Users VALUES ('Hamza Karachiwala');
INSERT INTO Users VALUES ('Rahul Bobhate');
INSERT INTO Users VALUES ('Hiranava Das');
INSERT INTO Users VALUES ('Sharique Hussain');
INSERT INTO Users VALUES ('Suryansh Singh');

INSERT INTO Courses VALUES ('Mobile Computing');
INSERT INTO Courses VALUES ('System Programming');
INSERT INTO Courses VALUES ('Data Structures');
INSERT INTO Courses VALUES ('Network Security');

INSERT INTO Libraries VALUES ('Marston');

INSERT INTO Rooms VALUES ('Raman',1);
INSERT INTO Rooms VALUES ('Edison',1);
INSERT INTO Rooms VALUES ('Carver',1);
INSERT INTO Rooms VALUES ('Cade',1);

INSERT INTO Spaces VALUES ('3D Print Area',1);

INSERT INTO UserCourse VALUES (4,2);
INSERT INTO UserCourse VALUES (4,3);
INSERT INTO UserCourse VALUES (1,1);
INSERT INTO UserCourse VALUES (2,4);
INSERT INTO UserCourse VALUES (3,3);

' Adding new columns '

Alter Table Users Add Ufid nvarchar(10);

Alter Table Users Add UserEmail nvarchar(50);

Alter Table Users Add UserMajor nvarchar(30);
