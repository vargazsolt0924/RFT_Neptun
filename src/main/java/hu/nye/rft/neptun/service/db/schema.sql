CREATE TABLE IF NOT EXISTS student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(255) NOT NULL,
    student_username VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS subject (
    subject_id INT PRIMARY KEY,
    subject_name VARCHAR(255) NOT NULL,
    subject_day_of_the_week VARCHAR(255) NOT NULL,
    subject_start_time VARCHAR(255) NOT NULL,
    subject_duration_in_minutes INT NOT NULL,
    subject_max_students INT NOT NULL,
    subject_credit INT NOT NULL
);

CREATE TABLE IF NOT EXISTS teacher (
    teacher_id INT PRIMARY KEY,
    teacher_name VARCHAR(255) NOT NULL,
    teacher_username VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS student_subject (
    student_student INT,
    subject_id INT,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
);

CREATE TABLE IF NOT EXISTS subject_teacher (
    subject_id INT,
    teacher_id INT,
    PRIMARY KEY (subject_id, teacher_id),
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);