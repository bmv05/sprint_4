//Выпадающий список в разделе «Вопросы о важном».
// Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.

package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import praktikum.helper.DriverHelper;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

public class CheckingTextInSectionFAQ {
    //Подключить правило наследования для запуска браузера
    @Rule
    public DriverHelper driverHelper = new DriverHelper();

    //Создать массив вопрос-ответ
    private final Object[][] answerOnQuestions = {
            {"0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {"1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {"2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {"3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {"4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {"5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {"6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {"7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
    };

    @Test
    public void checkTabFAQ() throws InterruptedException {
        MainPage mainPage = new MainPage(driverHelper.getDriver());

        mainPage.open();//Открыть страницу
        mainPage.scrollToFAQ(); //Пролистать до раздела "Вопросы о важном"

        //Кликнуть по вопросам в цикле
        for (int i = 0; i <= this.answerOnQuestions.length - 1; i++) {
            WebElement tabTitle = mainPage.getQuestion(i); //Взять вопрос
            WebElement tabDescription = mainPage.getAnswer(i); //Взять ответ
            mainPage.scrollToTabTitle(tabTitle); // Прокрутить страницу до вопроса
            mainPage.waitThatTabTitleDisplayed(i); // Убедиться, что вопрос отображается на странице
            tabTitle.click();
            mainPage.waitThatTabTextDisplayed(i); // Убедиться, что ответ отображается на странице
            String titleText = (String) this.answerOnQuestions[i][1]; //заполнить текст вопроса
            String tabText = (String) this.answerOnQuestions[i][2]; //заполнить текст ответа
            assertEquals(titleText, tabTitle.getText()); // сравнить ожидаемый и отображаемый вопрос
            assertEquals(tabText, tabDescription.getText()); // сравнить ожидаемый и отображаемый ответ
        }
    }
}

