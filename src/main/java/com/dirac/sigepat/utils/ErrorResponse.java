/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.utils;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ErrorResponse {
    private String message;
    
}