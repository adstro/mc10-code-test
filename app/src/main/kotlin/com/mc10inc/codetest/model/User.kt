package com.mc10inc.codetest.model

import java.util.*

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
data class User(val id: UUID,
                val accountId: UUID,
                val email: String,
                val firstName: String,
                val lastName: String,
                val locale: String,
                val timeZone: TimeZone,
                val legaleseVersionRequired: Int,
                val legaleseVersionAccepted: Int)