package wolfdriver.third.util.netty.nio.server;


import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wolf_z
 * @date 2018/9/14 17:34
 */
public class MultiplexerTimServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean stop;

    /**
     * 构造方法进行资源初始化。创建多路复用器Selector、ServerSocketChannel。
     * 系统资源初始化成功后，将ServerSocketChannel注册到Selector，监听SelectionKey.OP_ACCEPT操作位。
     * @param port
     */
    public MultiplexerTimServer(int port) {
        try {
            // 创建多路复用器
            selector = Selector.open();
            // 打开ServerSocketChannel，用于监听客户端的连接，是所有连接的副管道。
            serverChannel = ServerSocketChannel.open();
            // 绑定监听端口，设置连接为非阻塞模式
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port));
            // 将ServerSocketChannel注册到Reactor线程的多路复用Selector上，监听ACCEPT事件
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("The time server is start in port: "+ port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void run() {

        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();

                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {

            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] datas = new byte[readBuffer.remaining()];
                    readBuffer.get(datas);
                    String body = new String(datas, "UTF-8");
                    System.out.println("The time server reeive order: " + body);

                    String currentTime = "TIME".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD_TIME";
                    doWrite(sc, currentTime);
                } else if(readBytes < 0) {
                    key.cancel();
                    sc.close();
                } else {
                    // 读到0字节，忽略
                }
            }


        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException{
        if (StringUtils.isBlank(response)) {
            return;
        }

        byte[] datas = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(datas.length);
        writeBuffer.put(datas);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }


    public void stop() {
        this.stop = true;
    }

}
