package com.geekersquare.producerconsumer;

import com.geekersquare.producerconsumer.entity.DataIn;
import com.geekersquare.producerconsumer.entity.DataOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author GeekerSquare - 龙叔
 * @version v1.0
 * @email chenrulong0513.master@gmail.com
 * @home www.geekersquare.com
 * @github https://github.com/chenrulongmaster
 * @Description 启动
 * @date 2018/4/6 14:44
 */
@Component
public class StartJob {

    /**
     * 消费者
     */
    @Autowired
    private Consumer consumer;

    /**
     * 生产者
     */
    @Autowired
    private Producer producer;

    /**
     * 结果输出
     */
    @Autowired
    private Pipleline pipeline;

    @Value("${job.consumer.thread.size}")
    private int consumerThreadSize;

    @Value("${job.producer.thread.size}")
    private int producerThreadSize;

    @Value("${job.pipeline.thread.size}")
    private int pipelineThreadSize;

    private LinkedBlockingQueue<DataIn> inQueue;

    private LinkedBlockingQueue<DataOut> outQueue;

    private ExecutorService threadPool;

    private final AtomicBoolean stopFlag = new AtomicBoolean(false);

    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Got stop flag.. ");
                stopFlag.set(true);
            }
        });
        inQueue = new LinkedBlockingQueue<DataIn>(consumerThreadSize);
        outQueue = new LinkedBlockingQueue<DataOut>(pipelineThreadSize);
        threadPool = Executors.newFixedThreadPool(consumerThreadSize + producerThreadSize + pipelineThreadSize);
    }

    /**
     * 启动
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        // 启动pipeline线程
        for (int i = 0; i < pipelineThreadSize; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    pipeline();
                }
            });
        }
        // 启动 消费者线程
        for (int i = 0; i < consumerThreadSize; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    consume();
                }
            });
        }
        // 载入备份文件
        // TODO
        // 启动 生产者线程
        for (int i = 0; i < pipelineThreadSize; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    produce();
                }
            });
        }
        threadPool.isShutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    /**
     * 生产者
     */
    private void produce() {
        while (!stopFlag.get()) {
            try {
                DataIn data = producer.produce();
                if (data == null) {
                    Thread.sleep(2000);
                    System.out.println("No data produced..");
                } else {
                    inQueue.put(data);
                }
            } catch (Exception e) {
                // replace with your logging system
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费者
     */
    private void consume() {
        while (!stopFlag.get()) {
            try {
                DataIn data = inQueue.poll(2000, TimeUnit.MILLISECONDS);
                if (data != null) {
                    DataOut out = consumer.consume(data);
                    outQueue.put(out);
                } else {
                    System.out.println("No data consumed..");
                }
            } catch (Exception e) {
                // replace with your logging system
                e.printStackTrace();
            }
        }
    }

    /**
     * 结果输出
     */
    private void pipeline() {
        while (!stopFlag.get()) {
            try {
                DataOut out = outQueue.poll(2000, TimeUnit.MILLISECONDS);
                if (out != null) {
                    pipeline.out(out);
                } else {
                    System.out.println("No data pipelined..");
                }
            } catch (Exception e) {
                // replace with your logging system
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        StartJob job = ctx.getBean(StartJob.class);
        job.start();
        ctx.close();
    }

}
