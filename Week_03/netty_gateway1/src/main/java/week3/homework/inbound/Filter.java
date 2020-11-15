package week3.homework.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class Filter {
    public static void filter(FullHttpRequest fullRequest){
        fullRequest.headers().add("nio", "filter_value");
    }
}
