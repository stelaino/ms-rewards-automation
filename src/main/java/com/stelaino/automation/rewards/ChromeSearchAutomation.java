package com.stelaino.automation.rewards;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * @author stelaino
 *
 * System.setProperty("webdriver.chrome.driver", driverPath);
 * GlobalThreadPool.execute(() -> {
 *     ChromeViewUtil.doTask(cookieValue, executionCount, false);
 * });
 * GlobalThreadPool.execute(() -> {
 *     ChromeViewUtil.doTask(cookieValue, executionCount, true);
 * });
 */
public class ChromeSearchAutomation {
    /**
     * 任务执行
     *
     * @param cookieStr
     * @param taskTime
     * @param isMobile
     */
    public static void doTask(String cookieStr, int taskTime, boolean isMobile) {
        // 创建Edge浏览器驱动
        WebDriver driver = null;
        driver = buildChromeDriver(isMobile);

        // 打开网页
        driver.get("https://cn.bing.com/search?q=" + UUID.randomUUID());

        String[] cookies = cookieStr.split("; ");
        for (String cookie : cookies) {
            String[] cookieParts = cookie.split("=");
            if (cookieParts.length == 2) {
                String name = cookieParts[0];
                String value = cookieParts[1];
                Cookie seleniumCookie = new Cookie(name, value);
                driver.manage().addCookie(seleniumCookie);
            }
        }

        WebDriver useDriver = driver;

        IntStream.range(0, taskTime).forEach((i) -> {
            try {
                Thread.sleep(100 + new Random().nextInt(500));
                useDriver.get("https://cn.bing.com/search?q=" + UUID.randomUUID() + "&qs=ds&form=QBRE");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 关闭浏览器
        driver.quit();
    }

    private static WebDriver buildChromeDriver(boolean isMobile) {
        WebDriver driver;
        if (isMobile) {
            HashMap<String, String> mobileEmulation = new HashMap<>();
            //这里是要使用的模拟器名称
            mobileEmulation.put("deviceName", "iPhone X");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
            options.addArguments("user-agent=Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36 Edg/116.0.0.0");
            options.addArguments("--remote-allow-origins=*");
            return new ChromeDriver(options);

        }
        else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36");
            options.addArguments("--remote-allow-origins=*");
            return new ChromeDriver(options);
        }
    }
}
