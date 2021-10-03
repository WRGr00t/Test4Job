package com.example.bookcatalog.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class SearchBooksResponse {
    int count;
    ArrayList<BookResponse> books;
}
