package com.example.a_train.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Results {
    private Object data;
    public static Results rse(Object data){
        return new Results(data);
    }

}
