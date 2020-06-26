# Utility实验

## 实验目的

掌握常用的系统和数据集 Utility 的使用。

## 实验结果

实验后，学生应该掌握：

- 认识什么是 Utility，怎么使用 Utility。
- 掌握常用的分割数据集内容，复制数据集内容，对数据集内容进行排序等工作。。

## 实验指导

请参阅参考书。

>DFSMSdfp Utilities,Third Edition, September 2002,SC26-7414-02

## 实验要求

完成实验内容

## （可选）步骤1：使用 IEBPTPCH 和 IEBGENER 实用程序。

#### 实验准备：

系统管理员已经提前创建了两个分区数据集的文件ST034.DATA1(test1)、ST034.DATA1(test2)，包括了一些数据。

#### 实验步骤：
你可以通过以下JCL代码，将上述的文件复制到你的数据集中。
```
 //ST0XXA JOB ,’USER’,NOTIFY=&SYSUID,                 
 //     MSGLEVEL=(1,1),TIME=1                                  
 //STEP1 EXEC PGM=IEBGENER                                     
 //SYSPRINT DD SYSOUT=*                                        
 //SYSUT1 DD DSN=ST034.DATA1(TEST1),DISP=SHR                 
 //SYSUT2 DD DSN=ST0XX.DATA(TEST1),DISP=(NEW,CATLG,DELETE), 
 //  SPACE=(TRK,(1,1,1)),VOL=SER=BYWK00,                       
 //  RECFM=FB,LRECL=80,UNIT=SYSDA                              
 //SYSIN    DD  DUMMY                                          
 //STEP2 EXEC PGM=IEBPTPCH                                     
 //SYSPRINT DD SYSOUT=*                                        
 //SYSUT1 DD DSN=ST034.DATA1(TEST1),DISP=SHR                 
 //SYSUT2 DD DSN=ST0XX.DATA(TEST1),DISP=(OLD,CATLG,DELETE), 
 //    UNIT=SYSDA,VOL=SER=BYWK00                               
 //SYSIN    DD *                                               
    PRINT  TYPORG=PS,MAXFLDS=3                              
    RECORD FIELD=(7,9,,1),FIELD=(6,20,,11),FIELD=(3,28,,20)                                
/*
```
>JCL解释：使用IEBPTPCH 和 IEBGENER程序，将上述数据集文件拷贝到你的数据集中。              
## 步骤2：使用 SORT 和 IEBGENER 实用程序。

#### 实验准备：

你可以使用已有的ST034.DATA1(test1)与ST034.DATA1(test2)文件，如果你刚刚进行了步骤1，也可以使用你刚刚新建的文件。

#### 实验步骤：
你可以通过以下JCL代码，将上述的文件两个文件排序，并合并输出到数据集中。
```
//ST0XXA JOB ,’USER’,NOTIFY=&SYSUID,TIME=2             
//STEP1 EXEC PGM=SORT                                
//SORTIN DD DSN=ST034.DATA1(test1),DISP=SHR        
//       DD DSN=ST065.DATA1(test2),DISP=SHR        
//SORTOUT DD DSN=ST0XX.DATA(TEST),DISP=(NEW,,DELETE),
//     VOL=SER=BYWK00,UNIT=SYSDA                     
//SYSOUT DD SYSOUT=*                                 
//SYSIN DD *                                         
  SORT FIELDS=(2,5,CH,A)                             
  OUTFIL FNAMES=SORTOUT                                                                                            
/*
```
>JCL解释：使用SORT和IEBGENER实用程序，将上述数据集文件合并排序后输出到数据集中。              
## 步骤3：数据集查询。

#### 实验准备：

对上述输出的数据集进行查询，查看排序结果。

#### 实验步骤：
你可以通过以下JCL代码，将上述的文件两个文件排序，并合并输出到数据集中。
```
//ST0XXA JOB ,’USER’,NOTIFY=&SYSUID,TIME=2                                                
//STEP1 EXEC PGM=IDCAMS                                                 
//SYSPRINT DD SYSOUT=*                                                  
//SYSIN DD *                                                            
     LISTDSD DA('ST034') ALL                                               
/*
```
>JCL解释：查找数据集。              
