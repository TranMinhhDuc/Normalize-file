use normalize_file;

CREATE TABLE history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `describe` NVARCHAR(255) NOT NULL,
    time DATETIME NOT NULL,
    location VARCHAR(255) NOT NULL
);
