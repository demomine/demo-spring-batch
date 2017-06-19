# SPRING BATCH
##  job
1.  配置job必须的三个属性`name`,`jobRepository`,`step`s
2.  Restartability
    *   设置`SimpleJobBuilder.preventRestart()`阻止重启
3.  Job 执行
4.  job继承
5.  参数验证
6.  javaConfig
7.  JobRepository配置
8.  事务配置
9.  jobLauncher 每次执行需要新的JobParameters从而触发新的JobInstance