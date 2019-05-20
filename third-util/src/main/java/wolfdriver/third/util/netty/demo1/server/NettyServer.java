package wolfdriver.third.util.netty.demo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import wolfdriver.third.util.netty.demo1.server.heandler.ServerHandler;

import java.net.InetSocketAddress;

/**
 * @author wolf_z
 * @date 2018/9/13 14:46
 */
public class NettyServer {
    private final int port =  8888;



    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)  // 指定所使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 添加一个ChannelHandler到子Channel的ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new ServerHandler());
                        }
                    });
            // 异步地绑定服务器，调用sync方法阻塞等待直到绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            // 调用Channel的CloseFuture，并阻塞当前线程直到他完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭eventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws Exception{
        new NettyServer().start();
    }
}
