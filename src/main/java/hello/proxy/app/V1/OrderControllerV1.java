package hello.proxy.app.V1;

import org.springframework.web.bind.annotation.*;

@RequestMapping //@Controller 또는 @RequestMapping 이 있으면 Spring Controller로 인식합니다.
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("v1/no-log")
    String noLog();
}
