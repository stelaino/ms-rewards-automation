# Introduction
This document describes how to use Selenium to simulate Chrome browser actions for "Computer Search" and "Mobile Search" on Bing in order to earn Microsoft Rewards.

![image.png](https://raw.githubusercontent.com/stelaino/ms-rewards-automation/main/doc/image/daily%20search%20details.png)

# Environment Setup
## JDK
Use Java 8. Other versions are expected to be compatible as well (no specific recommendation, the author uses [Alibaba Dragonwell](https://dragonwell-jdk.io/#/index)).

## ChromeDriver
Check your current Chrome version (enter "chrome://version/" in the address bar) and download the corresponding version of ChromeDriver from [here](https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json). For more information about ChromeDriver, refer to [GoogleChromeLabs](https://github.com/GoogleChromeLabs/chrome-for-testing).

# Usage
- Select ChromeDriver as the driver option.
- Enter the access cookie in the Cookie field.
- Specify the number of search executions.
- Click "Confirm" to start simulating the specified number of searches on both computer and mobile devices.

![image.png](https://raw.githubusercontent.com/stelaino/ms-rewards-automation/main/doc/image/app%20window.png)

![image.png](https://raw.githubusercontent.com/stelaino/ms-rewards-automation/main/doc/image/demonstrate%20the%20effect.png)

# Note
**Cookie Acquisition:** Obtain the Cookie value from the server request's Header by performing a Bing search on your own.

![image.png](https://raw.githubusercontent.com/stelaino/ms-rewards-automation/main/doc/image/acquisition%20of%20cookies.png)