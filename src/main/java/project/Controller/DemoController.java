package project.Controller;

import shitty.web.annotation.Controller;
import shitty.web.annotation.Param;
import shitty.web.annotation.Post;
import shitty.web.http.HttpResponseUtil;

/**
 * program: shitty
 * description:
 * author: Makise
 * create: 2019-04-17 16:10
 **/
@Controller("/demo")
public class DemoController {
    @Post("/{name}/{count}")
    public HttpResponseUtil hello(@Param("name") String name, @Param("count") int count, @Param("token") String token) {
        StringBuilder hello = new StringBuilder("hello,").append(name).append("\n");
        for (; count > 0; count--) {
            hello.append("again\n");
        }
        hello.append(", token is ").append(token);
        return new HttpResponseUtil().putText(hello.toString());
    }
}
