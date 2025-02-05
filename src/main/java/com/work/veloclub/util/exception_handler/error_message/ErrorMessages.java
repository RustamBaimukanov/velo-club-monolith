package com.work.veloclub.util.exception_handler.error_message;

public class ErrorMessages {
    public static class UserErrors {
        public static final String NOT_FOUND = "Пользователь не найден.";

        public static final String UNIQUE_EMAIL_EXISTS = "Пользователь с данным email уже зарегистрирован!";

        public static final String UNIQUE_PHONE_NUMBER_EXISTS = "Пользователь с таким телефоном уже зарегистрирован!";

        public static final String UNIQUE_CREDENTIALS_EXISTS = "Пользователь с таким телефоном и почтой уже зарегистрирован!";
    }
    public static class AccessErrors {
        public static final String DENIED = "Доступ запрещен.";
    }

    public static class EventErrors {
        public static final String NOT_FOUND = "Мероприятие не найдено.";
    }

    public static class EventResultErrors {
        public static final String NOT_FOUND = "Результат мероприятия не найдено.";
    }

    public static class RaceErrors {
        public static final String NOT_FOUND = "Маршрут не найден.";
    }

    public static class TeamErrors {
        public static final String NOT_FOUND = "Команда не найдена.";
    }

    public static class CityErrors {
        public static final String NOT_FOUND = "Регион не найден.";
    }

    public static class CategoryErrors {
        public static final String NOT_FOUND = "Классификация не найдена.";
    }

    public static class NewsErrors {
        public static final String NOT_FOUND = "Новость не найдена.";
    }
}
