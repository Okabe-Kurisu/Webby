package shitty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import shitty.ShittyApplication;


/**
 * program: shitty
 * description:
 * author: Makise
 * create: 2019-02-26 12:12
 **/
public class HttpInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 获取通道
        ChannelPipeline p = channel.pipeline();
        // 添加http加解码器
        p.addLast(new HttpServerCodec());
        // 添加自定义的handler组件
        p.addLast(new HttpHandler());
        //把编码设置成utf-8
        p.addLast(new StringDecoder(ShittyApplication.config.getStringDecoder()));
        //添加gzip压缩
        p.addLast("compressor", new HttpContentCompressor());
        //开启http聚合
        p.addLast("aggregator", new HttpObjectAggregator(512 * 1024));

    }
}