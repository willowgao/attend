CREATE OR REPLACE PACKAGE BODY "PKG_SYNCHRONIZED_MENU" IS
  /**************************************************************************************/
  /* 版本说明: 同步此角色下所有的用户菜单信息                                           */
  /* 版 本 号: V1.0                                                                      */
  /* 作    者: GAOCHENGLIU                                                               */
  /* 编写时间: 2015-09-10                                                               */
  /* 修改时间:                                                                          */
  /* 修改人:                                                                             */
  /* 01   PRC_SYNCHRONIZEDMENUTOUSER   通过传入的角色ID，同步此角色下所有的用户菜单信息  */
  /***************************************************************************************/

  /***************************************************************************************/
  /* 方法序号: 01                                                                       */
  /* 方法名称: PRC_SYNCHRONIZEDMENUTOUSER                                               */
  /* 方法描述: 通过传入的角色ID，同步此角色下所有的用户菜单信息                         */
  /* 版 本 号: V1.0                                                                      */
  /* 作    者: GAOCHENGLIU                                                               */
  /* 编写时间: 2015-09-10                                                               */
  /* 修改时间:                                                                           */
  /* 修改人:                                                                             */
  /***************************************************************************************/
  /*  参数名称                   参数类型           输入输出          参数描述             */
  /*  PRM_ROLEID                 VARCHAR2            IN              角色ID               */
  /*  PRM_ERRORMSG               VARCHAR2            OUT             错误信息            */
  /*  PRM_ERRORNO                VARCHAR2            OUT             错误编号            */
  /*                                                                                     */
  /***************************************************************************************/

  PROCEDURE PRC_SYNCHRONIZEDMENUTOUSER(PRM_ROLEID   IN VARCHAR2,
                                       PRM_ERRORMSG OUT VARCHAR2,
                                       PRM_ERRORNO  OUT VARCHAR2)
  
   IS
    /**定义角色所属用户的游标**/
    CURSOR CUR_ROLES IS
      SELECT ROLEID, USERID, USERORG
        FROM USERINFO
       WHERE ROLEID = PRM_ROLEID;
  
  BEGIN
  
    FOR REC_ROLES IN CUR_ROLES LOOP
      BEGIN
        --根据角色菜单、写入用户菜单数据
        INSERT INTO USER_MENU
          (MENUID, USERID, USERORG)
          SELECT MENUID, REC_ROLES.USERID, REC_ROLES.USERORG
            FROM ROLE_MENU A
           WHERE ROLEID = PRM_ROLEID
             AND NOT EXISTS (SELECT 1
                    FROM USER_MENU
                   WHERE MENUID = A.MENUID
                     AND USERID = REC_ROLES.USERID);
      
      EXCEPTION
        WHEN OTHERS THEN
          GOTO LABLE;
      END;
      COMMIT;
      <<LABLE>>
      NULL;
    END LOOP;
  
  END PRC_SYNCHRONIZEDMENUTOUSER;

  /***************************************************************************************/
  /* 方法序号: 02                                                                       */
  /* 方法名称: PRC_SETCLOCKEXCEPTIONTONOMARL                                               */
  /* 方法描述: 通过传入的日期，用户ID、打卡日期                         */
  /* 版 本 号: V1.0                                                                      */
  /* 作    者: GAOCHENGLIU                                                               */
  /* 编写时间: 2016-04-11                                                               */
  /* 修改时间:                                                                           */
  /* 修改人:                                                                             */
  /***************************************************************************************/
  /*  参数名称                   参数类型           输入输出          参数描述             */
  /*  PRM_USERID                 VARCHAR2            IN              用户ID               */
  /*  PRM_clockdate              VARCHAR2                IN              打卡日期              */
  /*  PRM_ERRORMSG               VARCHAR2            OUT             错误信息            */
  /*  PRM_ERRORNO                VARCHAR2            OUT             错误编号            */
  /*                                                                                     */
  /***************************************************************************************/

  PROCEDURE PRC_SETCLOCKEXCEPTIONTONOMARL(PRM_USERID    IN VARCHAR2,
                                          PRM_CLOCKDATE IN VARCHAR2,
                                          PRM_ERRORMSG  OUT VARCHAR2,
                                          PRM_ERRORNO   OUT VARCHAR2)
  
   IS
  
    VAR_AMSB VARCHAR2(10); --上午上班时间
    VAR_AMXB VARCHAR2(10); --上午下班时间
    VAR_PMSB VARCHAR2(10); --下午上班时间
    VAR_PMXB VARCHAR2(10); --下午下班时间
  
    VAR_AMSBTIME VARCHAR2(10); --标准上午上班时间
    VAR_AMXBTIME VARCHAR2(10); --标准上午下班时间
    VAR_PMSBTIME VARCHAR2(10); --标准下午上班时间
    VAR_PMXBTIME VARCHAR2(10); --标准下午下班时间
  
    VAR_SYSDATE VARCHAR2(10) := TO_CHAR(SYSDATE, 'yyyy-mm-dd');
    var_clockdate  VARCHAR2(10) :=  SUBSTR(PRM_CLOCKDATE, 0,10);
  
  BEGIN
    SELECT AMSB, AMXB, PMSB, PMXB
      INTO VAR_AMSB, VAR_AMXB, VAR_PMSB, VAR_PMXB
      FROM CLOCKREOCRDS
     WHERE CLOCKDATE = TO_DATE(var_clockdate, 'yyyy-mm-dd')
       AND USERID = PRM_USERID;
  
    SELECT AMSBTIME, AMXBTIME, PMSBTIME, PMXBTIME
      INTO VAR_AMSBTIME, VAR_AMXBTIME, VAR_PMSBTIME, VAR_PMXBTIME
      FROM CLOCK_SETTING
     WHERE TO_DATE(var_clockdate, 'yyyy-mm-dd') BETWEEN STARTTIME AND
           ENDTIME
       AND ISENABLE = '0';
  
    IF VAR_AMSB IS NULL AND
       SYSDATE >
       TO_DATE(var_clockdate || VAR_AMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
    
      UPDATE CLOCKREOCRDS
         SET AMSB = VAR_AMSBTIME
       WHERE CLOCKDATE =  TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    
    END IF;
  
    IF VAR_AMXB IS NULL AND
       SYSDATE >
       TO_DATE(var_clockdate || VAR_AMXBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
    
      UPDATE CLOCKREOCRDS
         SET AMXB = VAR_AMXBTIME
       WHERE CLOCKDATE =  TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    
    END IF;
  
    IF VAR_PMSB IS NULL AND
       SYSDATE >
       TO_DATE(var_clockdate || VAR_PMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
    
      UPDATE CLOCKREOCRDS
         SET PMSB = VAR_PMSBTIME
       WHERE CLOCKDATE =  TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    
    END IF;
  
    IF VAR_PMXB IS NULL AND
       SYSDATE >
       TO_DATE(var_clockdate || VAR_PMXBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
    
      UPDATE CLOCKREOCRDS
         SET PMXB = VAR_PMXBTIME
       WHERE CLOCKDATE =  TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    
    END IF;
  
    --时间往前推10分钟还是迟到，则为异常打卡
    IF TO_DATE(VAR_SYSDATE || VAR_AMSB, 'YYYY-MM-DD HH24:Mi:SS') -
       10 / (60 * 24) >
       TO_DATE(var_clockdate || VAR_AMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
      UPDATE CLOCKREOCRDS
         SET AMSB = VAR_AMSBTIME
       WHERE CLOCKDATE =  TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    END IF;
  
    --时间往后推10分钟还是没有到打卡时间，则为异常打卡 或者下班打卡时间晚于下午上班时间，也为异常打卡
    IF TO_DATE(VAR_SYSDATE || VAR_AMXB, 'YYYY-MM-DD HH24:Mi:SS') +
       10 / (60 * 24) <
       TO_DATE(var_clockdate || VAR_AMXBTIME, 'YYYY-MM-DD HH24:Mi:SS')
      
       OR TO_DATE(VAR_SYSDATE || VAR_AMXB, 'YYYY-MM-DD HH24:Mi:SS') >
       TO_DATE(var_clockdate || VAR_PMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
      UPDATE CLOCKREOCRDS
         SET AMXB = VAR_AMXBTIME
       WHERE CLOCKDATE =  TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    END IF;
  
    --时间往前推10分钟还是迟到，则为异常打卡
    IF TO_DATE(VAR_SYSDATE || VAR_PMSB, 'YYYY-MM-DD HH24:Mi:SS') -
       10 / (60 * 24) >
       TO_DATE(var_clockdate || VAR_PMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
      UPDATE CLOCKREOCRDS
         SET PMSB = VAR_PMSBTIME
       WHERE CLOCKDATE = TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    END IF;
  
    --时间往后推10分钟还是没有到打卡时间，则为异常打卡
    IF TO_DATE(VAR_SYSDATE || VAR_PMXB, 'YYYY-MM-DD HH24:Mi:SS') +
       10 / (60 * 24) <
       TO_DATE(var_clockdate || VAR_PMXBTIME, 'YYYY-MM-DD HH24:Mi:SS') THEN
      UPDATE CLOCKREOCRDS
         SET PMXB = VAR_PMXBTIME
       WHERE CLOCKDATE = TO_DATE(var_clockdate, 'yyyy-mm-dd') 
         AND USERID = PRM_USERID;
    END IF;
  
    COMMIT;
  END PRC_SETCLOCKEXCEPTIONTONOMARL;

END PKG_SYNCHRONIZED_MENU;
/
show error;