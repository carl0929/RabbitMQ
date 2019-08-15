# RabbitMQ
rabbitMQ简单的在虚拟机上做了服务器，并在本地通信成功，
这是以到处Excle表格为基础做的，请忽略其他东西，主要看common里的配置和类

增加用户设置权限后：http://192.168.112.139:15672 
以自己服务器的Ip和端口进行访问（不要用默认的guest用户）

Ubuntu下载
sudo apt-get update
sudo apt-get install rabbitmq-server

启用后台
rabbitmq-plugins enable rabbitmq_management

查看状态
rabbitmqctl status


服务启动
service rabbitmq-server start

服务停止
rabbitmqctl stop

服务重启
service rabbitmq-server restart

用户管理
新增 rabbitmqctl add_user admin admin
删除 rabbitmqctl delete_user admin
修改 rabbitmqctl change_password admin admin123


用户列表 rabbitmqctl  list_users
设置角色 rabbitmqctl set_user_tags admin administrator monitoring policymaker management

设置用户权限 rabbitmqctl  set_permissions  -p  VHostPath  admin  ConfP  WriteP  ReadP
查询所有权限 rabbitmqctl  list_permissions  [-p  VHostPath]
指定用户权限 rabbitmqctl  list_user_permissions  admin
清除用户权限 rabbitmqctl  clear_permissions  [-p VHostPath]  admin

virtual_host管理
新建virtual_host: rabbitmqctl add_vhost xxx
撤销virtual_host: rabbitmqctl delete_vhost xxx
查看列表：rabbitmqctl list_vhosts

为用户赋予所有权限
sudo rabbitmqctl  set_permissions -p /vhost1  user_admin '.*' '.*' '.*'  

 队列管理
查看当前队列信息：rabbitmqctl list_queues
清除所有队列：rabbitmqctl reset
 获取服务器状态信息
查看节点信息：rabbitmqctl cluster_status
服务器状态：rabbitmqctl status
queue信息：rabbitmqctl list_queues	# 默认会查看 / 下的队列
# 如果想看其他vhost中的，加参数 -p "vhost_name"
exchange信息：rabbitmqctl list_exchanges
binding信息：rabbitmqctl list_bindings 
connection信息：rabbitmqctl list_connections 
channel信息：rabbitmqctl list_channels
 集群命令
 集群的事宜会有详解，暂略
查看集群状态：rabbitmqctl cluster_status
创建集群：rabbitmqctl join_cluster 节点@主机名

添加用户角色
rabbitmqctl set_user_tags test administrator



RabbitMQ各类角色描述：
none
不能访问 management plugin

management
用户可以通过AMQP做的任何事外加：
列出自己可以通过AMQP登入的virtual hosts  
查看自己的virtual hosts中的queues, exchanges 和 bindings
查看和关闭自己的channels 和 connections
查看有关自己的virtual hosts的“全局”的统计信息，包含其他用户在这些virtual hosts中的活动。

policymaker 
management可以做的任何事外加：
查看、创建和删除自己的virtual hosts所属的policies和parameters

monitoring  
management可以做的任何事外加：
列出所有virtual hosts，包括他们不能登录的virtual hosts
查看其他用户的connections和channels
查看节点级别的数据如clustering和memory使用情况
查看真正的关于所有virtual hosts的全局的统计信息

administrator   
policymaker和monitoring可以做的任何事外加:
创建和删除virtual hosts
查看、创建和删除users
查看创建和删除permissions
关闭其他用户的connections
