CREATE TABLE IF NOT EXISTS student (
    studentId INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS subject (
    subjectId INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dayOfTheWeek VARCHAR(255) NOT NULL,
    startTime VARCHAR(255) NOT NULL,
    durationInMinutes INT NOT NULL,
    maxStudent INT NOT NULL,
    kredit INT NOT NULL
);

CREATE TABLE IF NOT EXISTS teacher (
    teacherId INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS student_subject (
    studentId INT,
    subjectId INT,
    PRIMARY KEY (studentId, subjectId),
    FOREIGN KEY (studentId) REFERENCES student(studentId),
    FOREIGN KEY (subjectId) REFERENCES subject(subjectId)
);

CREATE TABLE IF NOT EXISTS subject_teacher (
    subjectId INT,
    teacherId INT,
    PRIMARY KEY (subjectId, teacherId),
    FOREIGN KEY (subjectId) REFERENCES subject(subjectId),
    FOREIGN KEY (teacherId) REFERENCES teacher(teacherId)
);