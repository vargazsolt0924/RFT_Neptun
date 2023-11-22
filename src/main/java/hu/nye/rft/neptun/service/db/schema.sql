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