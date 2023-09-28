package com.angel.youthmeeting.util.security;

import org.springframework.stereotype.Component;

@Component
public class EndPoints {

    public static final String YOUTH = "/youths";

    public static final String ADD_YOUTH = "";

    public static final String YOUTH_GET_ALL = "/all";

    public static final String GET_ALL = "";

    public static final String GET_YOUTH = "/{youthId}";

    public static final String EDIT_YOUTH = "";

    public static final String ADD_AREA = "";

    public static final String ADD_FAMILY = "";

    public static final String ADD_FATHER = "";

    public static final String ADD_SERVANT = "";

    public static final String SET_LANGUAGE = "/language";

    public static final String ADD_STREET = "";

    public static final String USER_REGISTER = "/sing-up";

    public static final String AREA = "/areas";

    public static final String FAMILY = "/families";

    public static final String FATHER = "/fathers";

    public static final String SERVANT = "/servants";

    public static final String SETTINGS = "/settings";

    public static final String STREET = "/streets";

    public static final String USER = "/users";

    private EndPoints() { }
}
