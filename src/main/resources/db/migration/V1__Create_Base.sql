CREATE TABLE activities (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  date_active BIGINT NOT NULL,
  login       VARCHAR(255),
  PRIMARY KEY (id)
);
CREATE TABLE comments (
  id               BIGINT       NOT NULL AUTO_INCREMENT,
  author           VARCHAR(255) NOT NULL,
  author_id        BIGINT       NOT NULL,
  date_create      BIGINT       NOT NULL,
  date_last_change BIGINT       NOT NULL,
  last_editor      VARCHAR(255),
  last_editor_id   BIGINT,
  text             LONGTEXT,
  patient_id       BIGINT,
  PRIMARY KEY (id)
);
CREATE TABLE hospital_users (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  date_birth VARCHAR(255) NOT NULL,
  login      VARCHAR(255) NOT NULL,
  name       VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  position   VARCHAR(255) NOT NULL,
  role_id    BIGINT       NOT NULL,
  surname    VARCHAR(255) NOT NULL,
  status_id  BIGINT,
  PRIMARY KEY (id)
);
CREATE TABLE patients (
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  date_birth    VARCHAR(255),
  date_transfer BIGINT       NOT NULL,
  name          VARCHAR(255) NOT NULL,
  surname       VARCHAR(255) NOT NULL,
  doctor_id     BIGINT,
  PRIMARY KEY (id)
);
CREATE TABLE user_role (
  id   BIGINT       NOT NULL AUTO_INCREMENT,
  role VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE user_status (
  id   BIGINT       NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
)