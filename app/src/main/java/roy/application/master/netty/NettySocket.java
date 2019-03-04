package roy.application.master.netty;

import com.socks.library.KLog;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import roy.application.master.utils.ThreadUtils;

public class NettySocket {

    private static final int CORE_POOL_SIZE = 1;

    private NioEventLoopGroup mEventExecutors;

    private ExecutorService mExecutorService;

    public  ChannelHandlerContext mChannelHandlerContext;

    private CallBack mCallBack;

    public NettySocket(CallBack callBack){
        this.mCallBack = callBack;
        if(null == mExecutorService){
            mExecutorService = ThreadUtils.getFixedPool(CORE_POOL_SIZE);
        }
    }

    public void startConnect(final int port,final String ip){
        try{
            mExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    mEventExecutors = new NioEventLoopGroup();
                    Bootstrap bootstrap = new Bootstrap();
                    bootstrap.channel(NioSocketChannel.class);
                    bootstrap.handler(mChannelInitializer);
                    bootstrap.group(mEventExecutors);
                    try {
                        Channel channel = bootstrap.connect(new InetSocketAddress(ip,port)).sync().channel();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ChannelInitializer mChannelInitializer = new ChannelInitializer() {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new IdleStateHandler(5,0,0, TimeUnit.SECONDS));
            ch.pipeline().addLast(mChannelInboundHandler);
            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,4,4,-8,0));
        }
    };

    private ChannelInboundHandler mChannelInboundHandler = new ChannelInboundHandler() {

//        private ByteBuf mByteBuf;

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            KLog.i("channelRegistered");
            mChannelHandlerContext = ctx;
        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            KLog.i("channelUnregistered");
            mChannelHandlerContext = ctx;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            KLog.i("channelActive");
            mChannelHandlerContext = ctx;
            if(null != mCallBack){
                mCallBack.onConnectState(true);
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            KLog.i("channelInactive");
            mChannelHandlerContext = ctx;
            if(null != mCallBack){
                mCallBack.onConnectState(false);
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            KLog.i("channelRead");
            mChannelHandlerContext = ctx;
            try{
               String body = (String) msg;
               mCallBack.onResponse(parseResponse(body));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            KLog.i("channelReadComplete");
            mChannelHandlerContext = ctx;
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            KLog.i("userEventTriggered");
            mChannelHandlerContext = ctx;
        }

        @Override
        public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
            KLog.i("channelWritabilityChanged");
            mChannelHandlerContext = ctx;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            KLog.i("exceptionCaught");
            mChannelHandlerContext = ctx;
            ctx.close();
            if(null != mCallBack) mCallBack.onFailure(cause);
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            KLog.i("handlerAdded");
            mChannelHandlerContext = ctx;
        }

        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            KLog.i("handlerRemoved");
            mChannelHandlerContext = ctx;
        }
    };

    public ChannelHandlerContext getChannelHandlerContext() {
        return mChannelHandlerContext;
    }

    public void stopConnected(){
        try{
            if(null != mEventExecutors){
                mEventExecutors.shutdownGracefully();
                mEventExecutors = null;
            }

            if(null != mExecutorService || mExecutorService.isShutdown()){
                mExecutorService.shutdown();
                mExecutorService = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendData(String message){
        try{
            if(null != mChannelHandlerContext){
                mChannelHandlerContext.writeAndFlush(message);
            }else{
                KLog.i("mChannelHandlerContext is null");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public NettyResponse parseResponse(String body){
        NettyResponse nettyResponse = new NettyResponse.Builder()
                .setContent(body)
                .builder();
        return nettyResponse;
    }
}
