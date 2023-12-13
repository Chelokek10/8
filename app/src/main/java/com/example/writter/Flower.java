package com.example.writter;

// Клас, що представляє квітку
public class Flower {

    private int id;           // Унікальний ідентифікатор квітки
    private String name;      // Назва квітки
    private String description; // Опис квітки

    // Конструктор класу для створення об'єкта Flower
    public Flower(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Метод для отримання ідентифікатора квітки
    public int getId() {
        return id;
    }

    // Метод для отримання назви квітки
    public String getName() {
        return name;
    }

    // Метод для отримання опису квітки
    public String getDescription() {
        return description;
    }
}
