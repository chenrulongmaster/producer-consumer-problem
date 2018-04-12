# Producer-Consumer-Problem sample
Java Concurrency programming - use producer-consumer-problem to improve performance.

![](https://github.com/chenrulongmaster/producer-consumer-problem/raw/master/doc/producer_consumer_prob.png)

Generically speaking, when setup a module in distributed system, we need to connect to the other modules through MQ middleware. For showing how the producer-consumer module works, this sample will use file system to replace the MQ middleware.

I integrate this sample with __Spring framework__, so you can just change the logic for consumer, producer and pipeline. Then use it in your company project easily.

There are still same reminder:

1. Don't forget to backup your __MESSAGE__ in producer(__Producer.java line56__).
2. Don't forget to delete your temporary file when you have done your task in consumer(__Consumer.java line34__).
3. Tune your thread size in __applicationContext.xml__.


---

Feel free to send email to me [chenrulong0513.master@gmail.com](mailto:chenrulong0513.master@gmail.com) if you have any questions. And welcome to my blog [GeekerSquare](http://www.geekersquare.com).

