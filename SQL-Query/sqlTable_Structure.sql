1)Table Name:Registrationinfo
Name                Null?    Type           
------------------- -------- -------------- 
STUDENT_ID          NOT NULL VARCHAR2(200)  
STUDENT_NAME                 VARCHAR2(200)  
STUDENT_EMAIL_ID             VARCHAR2(500)  
STUDENT_MOB_NO               VARCHAR2(200)  
STUDENT_ADDRESS              VARCHAR2(1000) 
STUDENT_CITY                 VARCHAR2(200)  
STUDENT_COUNTRY              VARCHAR2(200)  
STUDENT_GENDER               VARCHAR2(10)   
STUDENT_COURSE_NAME NOT NULL VARCHAR2(200)  
STUDENT_SEC                  VARCHAR2(200)  



2)Table Name:feestransaction
Name                       Null?    Type          
-------------------------- -------- ------------- 
STUDENT_ID                 NOT NULL VARCHAR2(200) 
STUDENT_COURSE_NAME        NOT NULL VARCHAR2(200) 
STUDENT_SEM_NAME           NOT NULL VARCHAR2(200) 
STUDENT_TFEE                        VARCHAR2(200) 
STUDENT_FINE                        VARCHAR2(200) 
STUDENT_SFEE                        VARCHAR2(200) 
STUDENT_FEE_SUBMISION_DATE          VARCHAR2(200) 
STUDENT_FEESTATUS                   VARCHAR2(200) 

3)Table Name:feesstructure
Name                       Null?    Type          
-------------------------- -------- ------------- 
COURSE_NAME                NOT NULL VARCHAR2(200) 
SEMESTER_NAME              NOT NULL VARCHAR2(200) 
FEES_AMOUNT                         VARCHAR2(200) 
FIRST_DATE_FEES_SUBMISSION          VARCHAR2(200) 

