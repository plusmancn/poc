package naming.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author plusman
 * @since 2021/4/13 9:23 PM
 */
@Slf4j
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            // Echo Server
            in.resetReaderIndex();
            ctx.writeAndFlush(in);
            // 如果没有配置 echo server 需要手动调用 release
            // ReferenceCountUtil.release(msg); // (2)
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       log.info("New Channel Active: {}", ctx);
        
        super.channelActive(ctx);
    }
}
