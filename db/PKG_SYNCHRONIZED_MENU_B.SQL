CREATE OR REPLACE PACKAGE BODY  PKG_SYNCHRONIZED_MENU IS
/**************************************************************************************/
/* 版本说明: 同步此角色下所有的用户菜单信息                                           */
/* 版 本 号: V1.0                                                                  		*/
/* 作    者: GAOCHENGLIU                                                               */
/* 编写时间: 2015-09-10                                                               */
/* 修改时间:                                                                     			*/
/* 修改人:                                                                       			*/
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

PROCEDURE  PRC_SYNCHRONIZEDMENUTOUSER( PRM_ROLEID     IN  VARCHAR2,
	                          					 PRM_ERRORMSG      OUT VARCHAR2,
	                          					 PRM_ERRORNO       OUT VARCHAR2)
	                          					 
	                          					 
IS
		/**定义角色所属用户的游标**/
    CURSOR CUR_ROLES IS 
    SELECT ROLEID,
           USERID,
           USERORG
      FROM USERINFO
     WHERE ROLEID = PRM_ROLEID; 

BEGIN
	
	  FOR REC_ROLES IN CUR_ROLES LOOP
	   BEGIN
	  	--根据角色菜单、写入用户菜单数据
	  	INSERT INTO USER_MENU(
	  	       MENUID,
	  	       USERID,
	  	       USERORG
	  	       )
	  	SELECT MENUID,
	  	       REC_ROLES.USERID,
	  	       REC_ROLES.USERORG
	  	  FROM ROLE_MENU A
	  	 WHERE ROLEID = PRM_ROLEID
	  	   AND NOT EXISTS (
	  	       SELECT 1 
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



END PKG_SYNCHRONIZED_MENU;
/
show error;