package edu.example.loginapp.filter.entities;

public record Pagination(int startingPosition, int numberOfResults, String value, String column, ProductOrder order) {
}
