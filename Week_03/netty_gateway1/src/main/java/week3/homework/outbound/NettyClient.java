package week3.homework.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class NettyClient {

    /**
     * @param ctx         used to write response
     * @param fullRequest 需要转发的请求
     */
    public void start(ChannelHandlerContext ctx, FullHttpRequest fullRequest, String targetUrl) {

    }
}
