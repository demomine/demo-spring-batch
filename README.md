#   spring batch
##  step
1.  step的处理方式包括 `trunk` 和 `tasklet`
2.  trunk方式由`read`,`write`和`process`组成
3.  每一个处理节点都可以使用`before`,`after`,进行监听
4.  可以实现抽象的step,并且merge父类的listener
5.  可以设置`commit-interval`一次性writer的条目数
6.  通过设置`StartLimit`来限制重启的次数,默认为Integer.MAX_VALUE
7.  通过设置`allow-start-if-complete`来设置是否允许重启
8.  通过`skip-limit`配置跳过的处理逻辑
9.  通过`retry-limit`配置重试限制
10. 通过`no-rollback-exception-classes`配置哪些异常不需要事务回滚
11. step的执行顺序支持 并行,条件,顺序
12. 通过`JobExecutionDecider`设置执行流程