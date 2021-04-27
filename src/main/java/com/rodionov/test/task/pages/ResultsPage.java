package com.rodionov.test.task.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.checkerframework.common.value.qual.StringVal;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class ResultsPage {

    private static final By ITEM = By.xpath(".//div[@data-component-type='s-search-result']");
    private static final By RESULT_NAME = By.xpath(".//div[@class='a-section a-spacing-none']/h2//span");
    private static final By RESULT_PRICE = By.xpath(".//span[@class='a-price']");

    @Step("Filter out the desired products according criteria: {0}")
    public List<SelenideElement> getResultsListForProduct(String productName) {
        return $$(ITEM).stream()
                .filter(item -> {
                    String itemName = item.find(RESULT_NAME).getText().trim().toLowerCase();
                    Set<Boolean> col = Arrays.stream(productName.trim().split(" "))
                            .map(String::toLowerCase)
                            .map(itemName::contains)
                            .collect(Collectors.toSet());
                    return col.size() == 1 && col.contains(true);
                })
                .collect(Collectors.toList());
    }

    @Step("Open the cheapest product for criteria: {0}")
    public ProductPage openTheCheapestProduct(String productName) {
        List<SelenideElement> products = getResultsListForProduct(productName);
        if (products.isEmpty())
            throw new AssertionError(String.format("No results found for criteria '%s'", productName));
        SelenideElement product = products.stream()
                .filter(item -> item.find(RESULT_PRICE).is(not(hidden)))
                .min(Comparator.comparingDouble(this::getPrice))
                .orElseThrow(() -> new AssertionError("no products found"));
        try {
            product.find(RESULT_NAME).click();
        } catch (Error ignored) {
            System.out.println(ignored);
        }
        return page(ProductPage.class);
    }

    @Step("Get product price")
    private double getPrice(SelenideElement element) {
        return Double.parseDouble(element.find(RESULT_PRICE).getText().replaceAll("[^0-9.]", ""));
    }
}
