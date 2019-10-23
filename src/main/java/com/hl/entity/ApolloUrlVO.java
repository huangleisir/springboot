package com.hl.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ApolloUrlVO implements Serializable {

    private static final long serialVersionUID = 674565351312511L;


    public String name;

    public String url;


}
