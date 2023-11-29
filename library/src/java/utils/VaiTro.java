/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Admin
 */
public enum VaiTro {
    BANDOC(0),
    NHANVIEN(1),
    ADMIN(2);
    
    private final int value;
    VaiTro(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
