package com.elmacbeto.myapplication.sis.utils


object Constants {

    const val BASE_URL = "https://api.datos.gob.mx/"
    const val PHOTO_AUTHENTICATION_DEFAULT ="https://dthezntil550i.cloudfront.net/41/latest/411907122032322370009339024/1280_960/b8e5bfb1-11f7-487e-85cf-7fab328ea6aa.png"
    const val DATABASE_NAME = "DATABASE_NAME"
    const val TABLE_FACTS_ENTITY = "FACTS"

    //EXTRAS
    const val NAVIGATION_STATE_EXTRA = "NAVIGATION_STATE_EXTRA"

    //END POINTS
    const val END_POINT_FACTS = "/v1/gobmx.facts"

    sealed class ListenerLoginDestination {
        data object DestinationFirstTime : ListenerLoginDestination()
        data object DestinationEmailAndPASSWORD : ListenerLoginDestination()
        data object DestinationTouchId : ListenerLoginDestination()
        data object DestinationGoogleSession : ListenerLoginDestination()
    }

    enum class LoginState(val value:Int){
        LOGOUT(0),
        LOGIN_WITH_GOOGLE(1),
        LOGIN_WITH_FINGERPRINT(2),
        LOGIN_WITH_PASSWORD(3)
    }
}

