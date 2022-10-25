package ru.bspl.pet.tradingmarket.models;

public enum Roles {
    REST, //Для пользователей REST API
    ADMIN, //Администратор
    THESAURUS, //Роль для заполнения справочной информации (контрагенты, номенклатура и пр.)
    TRADER //Роль для запуска торговой площадки, настройки ограничений для торгов и выгрузки заказов
}