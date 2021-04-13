package br.com.luzialabs.desafio.agenda.base;

import br.com.luzialabs.desafio.agenda.utils.JsonUtils;

public class ParseObjToJsonString {
    
    @Override
    public String toString() {
        return JsonUtils.parseToJsonString(this);
    }   
}
