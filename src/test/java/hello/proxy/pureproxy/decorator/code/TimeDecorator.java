package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        Long startTime = System.currentTimeMillis();
        String result = component.operation();
        Long endTime = System.currentTimeMillis();
        return result;
    }
}
