package org.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击间距中的 <icon src="AllIcons.Actions.Execute"/> 图标。
@SpringBootApplication
@ComponentScan(basePackages = {"org.user"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}