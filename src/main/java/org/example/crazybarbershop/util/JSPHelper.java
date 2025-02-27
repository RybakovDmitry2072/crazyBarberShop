package org.example.crazybarbershop.util;


import lombok.experimental.UtilityClass;

@UtilityClass
public class JSPHelper {
    private final static String JSP_FORMAT = "WEB-INF/view/%s.jsp";

    public String getPath(String jsp){
        return String.format(JSP_FORMAT,jsp);
    }
}

