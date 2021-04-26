CREATE SEQUENCE sensor_seq NOCACHE;

CREATE TABLE temp_hum_sensor (
     id      NUMBER       PRIMARY KEY
   , temp    NUMBER(5, 2) NOT NULL
   , hum     NUMBER       NOT NULL
   , sdate   DATE         NOT NULL
);

SELECT * FROM temp_hum_sensor;

INSERT INTO temp_hum_sensor VALUES(sensor_seq.NEXTVAL, 26.00, 40, SYSDATE);
INSERT INTO temp_hum_sensor VALUES(sensor_seq.NEXTVAL, 26.10, 45, SYSDATE);
INSERT INTO temp_hum_sensor VALUES(sensor_seq.NEXTVAL, 26.20, 50, SYSDATE);
INSERT INTO temp_hum_sensor VALUES(sensor_seq.NEXTVAL, 26.30, 55, SYSDATE);
INSERT INTO temp_hum_sensor VALUES(sensor_seq.NEXTVAL, 26.40, 60, SYSDATE);