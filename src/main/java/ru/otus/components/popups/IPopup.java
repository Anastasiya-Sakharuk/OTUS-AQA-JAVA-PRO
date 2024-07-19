package ru.otus.components.popups;

public interface IPopup<T> {
    T popupShouldNotBePresent();

    T popupShouldBeVisible();

}
