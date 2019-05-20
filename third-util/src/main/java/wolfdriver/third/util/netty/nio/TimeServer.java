package wolfdriver.third.util.netty.nio;

import wolfdriver.third.util.netty.nio.server.MultiplexerTimServer;

/**
 * @author wolf_z
 * @date 2018/9/14 17:33
 */
public class TimeServer {
    private final static int port = 8888;

    public static void main(String[] args) {
        // 创建Reactor线程，并启动Reactor线程
        MultiplexerTimServer timServer = new MultiplexerTimServer(port);
        new Thread(timServer, "NIO-TimerServer-001").start();
    }

}
