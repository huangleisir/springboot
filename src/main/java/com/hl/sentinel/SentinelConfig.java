package com.hl.sentinel;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SentinelConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @PostConstruct
    private void initRules() throws Exception {
        FlowRule rule1 = new FlowRule();
        rule1.setResource("test.hello");
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setCount(2);   // 每秒调用最大次数为 1 次  -- 这里是初始值，后面可以在启动后sentinel控制台调整这个值

        FlowRule rule2 = new FlowRule();
        rule2.setResource("hello");
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setCount(2);   // 每秒调用最大次数为 1 次   -- 这里是初始值，后面可以在启动后sentinel控制台调整这个值

        List<FlowRule> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);

        // 将控制规则载入到 Sentinel
        com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager.loadRules(rules);
    }
}
