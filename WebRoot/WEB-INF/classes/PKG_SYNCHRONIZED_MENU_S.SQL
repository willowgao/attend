CREATE OR REPLACE PACKAGE "PKG_SYNCHRONIZED_MENU" IS
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
                                       PRM_ERRORNO       OUT VARCHAR2);
                                       
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

PROCEDURE  PRC_SETCLOCKEXCEPTIONTONOMARL( PRM_USERID     IN  VARCHAR2,
                                       PRM_clockdate     IN  VARCHAR2,
                                       PRM_ERRORMSG      OUT VARCHAR2,
                                       PRM_ERRORNO       OUT VARCHAR2);


END PKG_SYNCHRONIZED_MENU;
/
show error;