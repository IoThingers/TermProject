USE [anyonethere]
GO
/****** Object:  Table [dbo].[COURSE]    Script Date: 17/04/2016 9:54:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[COURSE](
	[COURSE_ID] [int] IDENTITY(1,1) NOT NULL,
	[COURSE_NAME] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[COURSE_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[FRIENDSHIP]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FRIENDSHIP](
	[USERS_1] [int] NOT NULL,
	[USERS_2] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[USERS_1] ASC,
	[USERS_2] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
/****** Object:  Table [dbo].[GROUP]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[GROUP](
	[GROUP_ID] [int] IDENTITY(1,1) NOT NULL,
	[GROUP_NAME] [varchar](100) NOT NULL,
	[ROOM_ID] [int] NOT NULL,
	[CREATOR_ID] [int] NOT NULL,
	[COURSE_ID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[GROUP_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[GROUP_USER]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GROUP_USER](
	[GROUP_ID] [int] NOT NULL,
	[UFID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[GROUP_ID] ASC,
	[UFID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
/****** Object:  Table [dbo].[LIBRARY]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LIBRARY](
	[LIBRARY_ID] [int] IDENTITY(1,1) NOT NULL,
	[LIBRARY_NAME] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LIBRARY_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ROOM]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ROOM](
	[ROOM_ID] [int] IDENTITY(1,1) NOT NULL,
	[ROOM_NAME] [varchar](100) NOT NULL,
	[LIBRARY_ID] [int] NOT NULL,
	[AVAILABILITY] [bit] NOT NULL,
	[LEVEL] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ROOM_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SECTION]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SECTION](
	[SECTION_ID] [int] IDENTITY(1,1) NOT NULL,
	[CAPACITY] [int] NOT NULL,
	[COUNT] [int] NOT NULL,
	[NAME] [varchar](100) NOT NULL,
	[LIBRARY_ID] [int] NOT NULL,
	[LEVEL] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SECTION_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USERS]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[USERS](
	[UFID] [int] NOT NULL,
	[USER_NAME] [varchar](100) NOT NULL,
	[USER_MAJOR] [varchar](100) NOT NULL,
	[USER_ACTIVE] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UFID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USERS_COURSE]    Script Date: 17/04/2016 9:54:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USERS_COURSE](
	[UFID] [int] NOT NULL,
	[COURSE_ID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UFID] ASC,
	[COURSE_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
ALTER TABLE [dbo].[FRIENDSHIP]  WITH CHECK ADD  CONSTRAINT [fk_User1] FOREIGN KEY([USERS_1])
REFERENCES [dbo].[USERS] ([UFID])
GO
ALTER TABLE [dbo].[FRIENDSHIP] CHECK CONSTRAINT [fk_User1]
GO
ALTER TABLE [dbo].[FRIENDSHIP]  WITH CHECK ADD  CONSTRAINT [fk_User2] FOREIGN KEY([USERS_2])
REFERENCES [dbo].[USERS] ([UFID])
GO
ALTER TABLE [dbo].[FRIENDSHIP] CHECK CONSTRAINT [fk_User2]
GO
ALTER TABLE [dbo].[GROUP]  WITH CHECK ADD  CONSTRAINT [fk_CourseGroup] FOREIGN KEY([COURSE_ID])
REFERENCES [dbo].[COURSE] ([COURSE_ID])
GO
ALTER TABLE [dbo].[GROUP] CHECK CONSTRAINT [fk_CourseGroup]
GO
ALTER TABLE [dbo].[GROUP]  WITH CHECK ADD  CONSTRAINT [fk_RoomGroup] FOREIGN KEY([ROOM_ID])
REFERENCES [dbo].[ROOM] ([ROOM_ID])
GO
ALTER TABLE [dbo].[GROUP] CHECK CONSTRAINT [fk_RoomGroup]
GO
ALTER TABLE [dbo].[GROUP]  WITH CHECK ADD  CONSTRAINT [fk_UserGroup] FOREIGN KEY([CREATOR_ID])
REFERENCES [dbo].[USERS] ([UFID])
GO
ALTER TABLE [dbo].[GROUP] CHECK CONSTRAINT [fk_UserGroup]
GO
ALTER TABLE [dbo].[GROUP_USER]  WITH CHECK ADD  CONSTRAINT [fk_Group_User_Group] FOREIGN KEY([GROUP_ID])
REFERENCES [dbo].[GROUP] ([GROUP_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GROUP_USER] CHECK CONSTRAINT [fk_Group_User_Group]
GO
ALTER TABLE [dbo].[GROUP_USER]  WITH CHECK ADD  CONSTRAINT [fk_Group_User_User] FOREIGN KEY([UFID])
REFERENCES [dbo].[USERS] ([UFID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GROUP_USER] CHECK CONSTRAINT [fk_Group_User_User]
GO
ALTER TABLE [dbo].[ROOM]  WITH CHECK ADD  CONSTRAINT [fk_LibRoom] FOREIGN KEY([LIBRARY_ID])
REFERENCES [dbo].[LIBRARY] ([LIBRARY_ID])
GO
ALTER TABLE [dbo].[ROOM] CHECK CONSTRAINT [fk_LibRoom]
GO
ALTER TABLE [dbo].[SECTION]  WITH CHECK ADD  CONSTRAINT [fk_LibSection] FOREIGN KEY([LIBRARY_ID])
REFERENCES [dbo].[LIBRARY] ([LIBRARY_ID])
GO
ALTER TABLE [dbo].[SECTION] CHECK CONSTRAINT [fk_LibSection]
GO
ALTER TABLE [dbo].[USERS_COURSE]  WITH CHECK ADD  CONSTRAINT [fk_Course] FOREIGN KEY([COURSE_ID])
REFERENCES [dbo].[COURSE] ([COURSE_ID])
GO
ALTER TABLE [dbo].[USERS_COURSE] CHECK CONSTRAINT [fk_Course]
GO
ALTER TABLE [dbo].[USERS_COURSE]  WITH CHECK ADD  CONSTRAINT [fk_User] FOREIGN KEY([UFID])
REFERENCES [dbo].[USERS] ([UFID])
GO
ALTER TABLE [dbo].[USERS_COURSE] CHECK CONSTRAINT [fk_User]
GO
ALTER DATABASE [anyonethere] SET  READ_WRITE 
GO

INSERT INTO [dbo].[LIBRARY]([LIBRARY_NAME])VALUES('Marston Science Library'),('George Smathers Library'),('Library West');

INSERT INTO [dbo].[ROOM]([ROOM_NAME],[LIBRARY_ID],[AVAILABILITY],[LEVEL])VALUES
('Raman',1,1,1),('Tesla',1,1,1),('Heisenberg',1,1,1),('Einstein',1,2,1),('Newton',3,1,1);

INSERT INTO [dbo].[COURSE]([COURSE_NAME])VALUES('Analysis of Algorithms'),('Computer Graphics'),('Network Security'),('Pattern Recognition'),
('Advanced Data Structures'),('Programming Principles'),('Distributed Operating Systems'),('Mobile Computing');

INSERT INTO [dbo].[SECTION]([CAPACITY],[COUNT],[NAME],[LIBRARY_ID],[LEVEL])VALUES
           (120,105,'Section 1',1,1),(65,30,'Section 2',1,1),(80,20,'Section 3',1,1),(180,100,'Graduate Section',3,1);
		   
INSERT INTO [dbo].[USERS]([UFID],[USER_NAME],[USER_MAJOR],[USER_ACTIVE])VALUES
           (61961544,'Shaun Pollock','Computer Science',1),
		   (25418954,'Shaun Marsh','Computer Science',1),
		   (32785417,'Jacques Kallis','Computer Science',1),
		   (99812356,'Lance Klusener','Computer Science',1),
		   (17664329,'Courtney Walsh','Computer Science',1),
		   (13226570,'Curtley Ambrose','Computer Science',1),
		   (10006432,'Vivian Richards','Computer Science',1),
		   (20973411,'Steve Waugh','Electrical Engineering',1),
		   (25119099,'Roger Federer','Electrical Engineering',1),
		   (75518912,'Usain Bolt','Electrical Engineering',1),
		   (22214090,'Michael Schumacher','Electrical Engineering',1),
		   (26358052,'Ian Thorpe','Electrical Engineering',1),
		   (26443928,'Novak Djokovic','Philosophy',1),
		   (61467769,'David Gough','Philosophy',1),
		   (76109998,'Donald Bradman','Philosophy',1),
		   (51446916,'James Weety','Philosophy',1),
		   (22804376,'Harry Potter','ISOM',1),
		   (19087234,'Tom Riddle','ISOM',1),
		   (44892222,'Ronaldinho Weasley','ISOM',1),
		   (67901465,'Hermione Granger','ISOM',1);

INSERT INTO [dbo].[USERS_COURSE]([UFID],[COURSE_ID])VALUES
           (10006432,2),
		   (10006432,3),
		   (10006432,6),
		   (10006432,7),
		   (13226570,5),
		   (13226570,2),
		   (13226570,8),
		   (17664329,1),
		   (19087234,3),
		   (19087234,4),
		   (20973411,2),
		   (20973411,3),
		   (22214090,6),
		   (22804376,7),
		   (22804376,5),
		   (22804376,2),
		   (25119099,8),
		   (25119099,1),
		   (25418954,3),
		   (25418954,4),
		   (26358052,2),
		   (26358052,3),
		   (26358052,6),
		   (26443928,7),
		   (26443928,5),
		   (32785417,2),
		   (32785417,8),
		   (32785417,1),
		   (32785417,3),
		   (44892222,4),
		   (44892222,2),
		   (44892222,3),
		   (51446916,6),
		   (51446916,7),
		   (61467769,5),
		   (61961544,2),
		   (67901465,8),
		   (75518912,1),
		   (76109998,3),
		   (99812356,4);
		   
INSERT INTO [dbo].[FRIENDSHIP]([USERS_1],[USERS_2])VALUES
		   (10006432,51446916),
		   (10006432,22804376),
           (10006432,76109998),
           (10006432,67901465),
           (10006432,99812356),
		   (13226570,17664329),
		   (13226570,20973411),
           (13226570,32785417),
           (13226570,67901465),
		   (13226570,44892222),
		   (17664329,32785417),
		   (17664329,25119099),
           (19087234,99812356),
           (19087234,26443928),
           (20973411,75518912),
		   (22214090,26443928),
		   (22214090,99812356),
           (22214090,32785417),
           (22804376,67901465),
           (22804376,99812356),
		   (25119099,51446916),
		   (25119099,32785417),
           (25119099,13226570),
           (25418954,67901465),
		   (26358052,99812356),
		   (26443928,17664329),
		   (26443928,76109998),
           (26443928,44892222),
		   (26443928,67901465),
           (32785417,99812356),
		   (32785417,44892222),
		   (44892222,67901465),
           (51446916,51446916),
           (51446916,10006432),
		   (61961544,61467769),
		   (67901465,26443928),
		   (75518912,99812356),
           (75518912,32785417),
           (76109998,67901465),
           (51446916,99812356);

INSERT INTO [dbo].[GROUP]([GROUP_NAME],[ROOM_ID],[CREATOR_ID],[COURSE_ID])VALUES
           ('PLP Project',1,61961544,6),
           ('AOA Exam Prep',2,26443928,1),
		   ('Pattern Rec Present',3,22214090,4),           
		   ('Hamza Code Prac',4,51446916,3),
           ('ADS Assignment',5,32785417,5);


UPDATE [dbo].[ROOM] SET AVAILABILITY=0;

INSERT INTO [dbo].[GROUP_USER]([GROUP_ID],[UFID])VALUES
           (1,61961544),
           (2,26443928),
           (3,22214090),
           (4,51446916),
           (5,32785417),
           (1,61467769),
           (2,44892222),
           (3,99812356),
           (4,25119099),
           (5,13226570),
           (2,67901465),
           (5,75518912),
           (4,19087234),
           (4,10006432);

ALTER TABLE USERS ADD COLUMN USER_EMAIL nvarchar(100));
ALTER TABLE GROUP_USER ADD CONSTRAINT unique_Ufid UNIQUE (UFID);



