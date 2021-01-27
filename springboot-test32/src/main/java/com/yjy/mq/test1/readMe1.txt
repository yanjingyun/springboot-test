##测试1：1个producer && 5个consumer
首先启动一个producer不断往4个队列发送数据；
启动一个consumer，这样四个队列都能消费；
启动2个consumer，每个consumer各消费两个队列；
启动3个consumer，一个consumer消费两个队列，其余两个consumer各消费一个队列；
启动4个consumer，每个consumer个消费一个队列；
启动5个consumer，有一个队列被两个consumer消费；关闭某一个consumer，每个consumer各消费一个队列。

##测试2：2个producer && 3个consumer（感觉是个问题！）
首先启动两个producer不断往4个队列发送数据；
启动1个consumer，这样四个队列都能消费；
启动3个consumer，一个consumer消费两个队列，其余两个consumer各消费一个队列；
将topic里面的 写队列数 从4改成2，一个consumer消费两个队列，其余两个consumer停止消费；
停掉消费两个队列的consumer，其余两个的某一个consumer开始消费两个队列，某一个consumer还是停止消费。

##测试3：写队列数为2，读队列数为4，2个producer，2个consumer
启动两个producer不断往两个队列发送数据
启动一个consumer，不断消费两个队列；
启动两个consumer，一个consumer消费两个队列，一个consumer并没有队列消费。
将读队列数从4改为2，之前没消费的consumer立即开始消费一个队列，而消费两个队列的consumer还是消费两个队列，但到后面变成只消费一个队列（可能后面发送心跳的时候检测到了）。
将读队列数从2改成4，再重启一个consumer3，consumer3&consumer2并无队列消费，consumer1消费两个队列。
将写队列数从2改成4，consumer1消费队列0和队列1，consumer2消费队列2，consumer3消费队列3

##测试4：写队列数为2，读队列数为4，2个producer（不同生产组），4个consumer（同一消费组）
两个producer不断往同一topic的队列0和队列1发送数据；
一个consumer消费队列0，一个consumer消费队列1，其余两个consumer不消费。
关闭某一consumer，发现一个consumer消费两个队列，其余两个consumer不消费。
重启上面consumer，一consumer消费队列0，一consumer消费队列1，其余两个consumer不消费。

##测试5：写队列数为2，读队列数为2，2个producer（不同生产组），2个consumer（消费组1），2个consumer（消费组2）
两个producer不断往同一topic的队列0和队列1发送数据；
消费组1的每个consumer分别消费某一队列的所有消息；
消费组2的每个consumer分别消费某一队列的所有消息；
新启动消费组1的consumer，发现该consumer并不消费队列。
关闭消费组1的两个consumer，此时消费组1仅有1个consumer，消费了两个队列。
结论：不同消费组能消费同一topic的同一队列里面的同一条消息


结论：
1）读写队列数配置：若写队列数<读队列数时，多出的读队列将无写队列匹配。若写队列数>读队列数，多出的写队列将不被消费。最佳配置为写队列数=读队列数。
2）写队列数与生产者数配置：生产者数不管多少，都是轮询所有写队列生产消息。
3）读队列数与消费者数配置：若读队列数<消费者数，多余的消费者不消费。最佳配置为读队列数>=消费者数，相等时每个消费者分配一个读队列，大于时多余的队列数再分配给部分消费者。

