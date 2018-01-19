package com.mc10inc.codetest.api

import com.mc10inc.codetest.model.User
import org.threeten.bp.Instant

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
data class LoginResponse(val user: User,
                         val accessToken: String,
                         val expiration: Instant)