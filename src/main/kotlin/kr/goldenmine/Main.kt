package kr.goldenmine

import io.github.bonigarcia.wdm.WebDriverManager
import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import kotlin.math.log

fun main() {
    val accountText = File("Account.txt").readLines()
    val id = accountText[0]
    val pw = accountText[1]

    WebDriverManager.chromedriver().setup()

    val driver = ChromeDriver()
    driver.get("https://solved.ac/login?prev=%2F")
    Thread.sleep(1000L)

    login(driver, id, pw)
    Thread.sleep(1000L)

    redirect(driver)
    Thread.sleep(10000L)

    driver.quit()
}

fun login(driver: WebDriver, id: String, pw: String) {
    val regPage = driver.findElement(By.className("reg-page"))

    val inputs = regPage.findElements(By.tagName("input"))

    inputs[0].sendKeys(id)
    inputs[1].sendKeys(pw)

    val loginButton = regPage.findElement(By.tagName("button"))
    loginButton.click()
}

fun redirect(driver: WebDriver) {
    val regPage = driver.findElement(By.className("reg-page"))
    val button = regPage.findElements(By.tagName("div")).first { it.text.contains("로그인") }
    button.click()
}