package com.example.demo.model;

public enum SaleState {
    ZAMOWIONY{
        @Override
        public String toString() {
            return "zamowiony";
        }
    },
    W_MAGAZYNIE {
        @Override
        public String toString() {
            return "w magazynie";
        }
    },
    SPRZEDANY {
        @Override
        public String toString() {
            return "sprzedany";
        }
    }
}
