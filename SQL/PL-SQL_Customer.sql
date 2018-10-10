--SEQUENCE USED TO AUTO INCREMENT
CREATE SEQUENCE CUSTOMER_SEQ
  START WITH 1
  INCREMENT BY 1;
  
--TRIGGER THAT WILL INCREMENT THE SEQUENCE
--AND ALTER THE PRIMARY KEY VALUE OF AN INSERT
CREATE OR REPLACE TRIGGER CUSTOMER_INSERT
BEFORE INSERT
ON CUSTOMER
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  --IF YOU DON'T WANT USERS TO INSERT ANY VALUE TO THE PK
  --COLUMN, REMOVE THE IF STATEMENT.
  IF :NEW.C_ID IS NULL THEN
    SELECT CUSTOMER_SEQ.NEXTVAL INTO :NEW.C_ID FROM DUAL;
  END IF;

  SELECT GET_CUSTOMER_HASH(:NEW.C_FIRST_NAME, :NEW.C_LOGIN_NAME) INTO :NEW.CUSTOMER_HASH FROM DUAL;
END;

--HASHING FUNCTION (Uses MD5)
CREATE OR REPLACE FUNCTION GET_CUSTOMER_HASH (FIRST_NAME VARCHAR2, LOGIN VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(20) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => FIRST_NAME || LOGIN || EXTRA)));
END;

--USED FOR CALLABLE STATEMENT IN JDBC
  CREATE OR REPLACE PROCEDURE INSERT_CUSTOMER (FIRST_NAME VARCHAR2, LAST_NAME VARCHAR2, LOGIN VARCHAR2, ACCOUNT_NUMBER NUMBER, EMAIL VARCHAR2)
  AS
  BEGIN
    INSERT INTO CUSTOMER VALUES(NULL, FIRST_NAME, LAST_NAME, LOGIN, ACCOUNT_NUMBER, EMAIL, NULL);
    COMMIT;
  END;